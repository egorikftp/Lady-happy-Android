package com.egoriku.mainscreen.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.connector.IDynamicFeatureConnector
import com.egoriku.core.di.utils.INavigationHolder
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.extensions.*
import com.egoriku.ladyhappy.navigation.navigator.platform.ActivityScopeNavigator
import com.egoriku.mainscreen.R
import com.egoriku.mainscreen.databinding.ActivityMainBinding
import com.egoriku.mainscreen.presentation.dynamicfeature.DynamicFeatureViewModel
import com.egoriku.mainscreen.presentation.inAppUpdates.InAppUpdate
import com.egoriku.mainscreen.presentation.inAppUpdates.InAppUpdateState.*
import com.egoriku.mainscreen.presentation.inAppUpdates.UPDATE_REQUEST_CODE
import com.egoriku.mainscreen.presentation.screen.*
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main), IDynamicFeatureConnector {

    private val binding: ActivityMainBinding by viewBinding(R.id.contentFullScreen)

    private val featureProvider: IFeatureProvider by inject()
    private val inAppUpdate: InAppUpdate by inject()
    private val navigatorHolder: INavigationHolder by inject()

    private val dynamicFeatureViewModel: DynamicFeatureViewModel by lifecycleScope.viewModel(this)
    private val viewModel: MainActivityViewModel by lifecycleScope.viewModel(this)

    private val navigator = ActivityScopeNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!hasM()) {
            window.statusBarColor = Color.BLACK
        }

        setSupportActionBar(binding.toolbarMainActivity)

        viewModel.screenTitle.observe(this) {
            binding.toolbarContent.headerBarLogoText.setText(it)
        }

        when (savedInstanceState) {
            null -> {
                viewModel.replaceWith(CatalogScreen(featureProvider))
                initInAppUpdate()
            }
            else -> {
                with(savedInstanceState.getInt(KEY_SELECTED_MENU_ITEM)) {
                    binding.bottomNavigation.selectedItemId = this
                }
            }
        }

        with(binding.bottomNavigation) {
            setOnNavigationItemSelectedListener { item ->
                consume {
                    mapItemIdToScreen(item.itemId)
                }
            }

            setOnNavigationItemReselectedListener {}
        }

        dynamicFeatureViewModel.installStatus.observe(this, EventObserver {
            when (it) {
                true -> onSuccessfulLoad()
                false -> toast("error")
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            UPDATE_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> viewModel.trackInAppUpdateSuccess()
                    Activity.RESULT_CANCELED -> viewModel.trackInAppUpdateCanceled()
                    ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> viewModel.trackInAppUpdateFailed()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        inAppUpdate.unsubscribe()
        super.onDestroy()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_SELECTED_MENU_ITEM, binding.bottomNavigation.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        SplitCompat.installActivity(this)
    }

    override fun installDynamicFeature(featureName: String) =
            dynamicFeatureViewModel.installDynamicFeature(featureName)

    private fun mapItemIdToScreen(@IdRes menuItemId: Int) {
        when (menuItemId) {
            R.id.menuLanding -> viewModel.replaceWith(LandingScreen(featureProvider))
            R.id.menuPhotoReport -> viewModel.replaceWith(PhotoReportScreen(featureProvider))
            R.id.menuCatalog -> viewModel.replaceWith(CatalogScreen(featureProvider))
            R.id.menuSettings -> viewModel.replaceWith(SettingsScreen(featureProvider))
        }
    }

    private fun onSuccessfulLoad() {
        viewModel.navigateTo(screen = PostCreatorScreen(), containerId = R.id.contentFullScreen)
    }

    private fun initInAppUpdate() {
        inAppUpdate.checkForUpdates()

        inAppUpdate.status.observe(this, EventObserver { status ->
            when (status) {
                is OnFailed -> {
                    binding.bottomNavigation.longSnackBar(
                            message = R.string.in_app_update_download_failed,
                            actionText = R.string.in_app_update_retry
                    ) {
                        inAppUpdate.checkForUpdates()
                    }
                }
                is Downloaded -> {
                    binding.bottomNavigation.indefiniteSnackBar(
                            message = R.string.in_app_update_download_finished,
                            actionText = R.string.in_app_update_restart
                    ) {
                        inAppUpdate.completeUpdate()
                    }
                }
                is RequestFlowUpdate -> {
                    inAppUpdate.startUpdateFlow(this, status.updateInfo)
                }
            }
        })
    }

    companion object {
        private const val KEY_SELECTED_MENU_ITEM = "selected_item"
    }
}