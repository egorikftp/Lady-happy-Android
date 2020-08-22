package com.egoriku.mainscreen.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.observe
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.IFeatureProvider
import com.egoriku.core.INavigationHolder
import com.egoriku.core.connector.IDynamicFeatureConnector
import com.egoriku.core.feature.AboutUsFeature
import com.egoriku.core.feature.CatalogFeature
import com.egoriku.core.feature.PhotoReportsFeature
import com.egoriku.core.feature.SettingsFeature
import com.egoriku.core.sharedmodel.toNightMode
import com.egoriku.extensions.*
import com.egoriku.ladyhappy.navigation.navigator.platform.ActivityScopeNavigator
import com.egoriku.mainscreen.R
import com.egoriku.mainscreen.databinding.ActivityMainBinding
import com.egoriku.mainscreen.presentation.dynamicfeature.DynamicFeatureViewModel
import com.egoriku.mainscreen.presentation.inAppReview.ReviewViewModel
import com.egoriku.mainscreen.presentation.screen.*
import com.egoriku.mainscreen.presentation.viewmodel.inAppUpdates.InAppUpdateEvent
import com.egoriku.mainscreen.presentation.viewmodel.inAppUpdates.InAppUpdateViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.bytesDownloaded
import com.google.android.play.core.ktx.totalBytesToDownload
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import kotlin.properties.Delegates
import androidx.lifecycle.lifecycleScope as activityLifecycle

private const val UPDATE_CONFIRMATION_REQ_CODE = 2
private const val KEY_SELECTED_MENU_ITEM = "selected_item"

class MainActivity : AppCompatActivity(R.layout.activity_main), IDynamicFeatureConnector {

    private val binding: ActivityMainBinding by viewBinding(R.id.contentFullScreen)

    private val appUpdateManager: AppUpdateManager by inject()
    private val featureProvider: IFeatureProvider by inject()
    private val navigatorHolder: INavigationHolder by inject()

    private val dynamicFeatureViewModel: DynamicFeatureViewModel by lifecycleScope.viewModel(this)
    private val inAppUpdateViewModel: InAppUpdateViewModel by lifecycleScope.viewModel(this)
    private val reviewViewModel: ReviewViewModel by lifecycleScope.viewModel(this)
    private val viewModel: MainActivityViewModel by lifecycleScope.viewModel(this)

    private val navigator = ActivityScopeNavigator(this, R.id.container)

    private var snackBar: Snackbar by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!hasM()) {
            window.statusBarColor = Color.BLACK
        }

        setSupportActionBar(binding.toolbarMainActivity)

        snackBar = Snackbar.make(
                binding.bottomNavigation,
                R.string.in_app_update_available,
                Snackbar.LENGTH_INDEFINITE
        )

        viewModel.screenTitle.observe(owner = this) {
            binding.toolbarContent.headerBarLogoText.setText(it)
        }

        when (savedInstanceState) {
            null -> viewModel.replaceWith(CatalogScreen(featureProvider))
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

        viewModel.theme.observe(owner = this) {
            AppCompatDelegate.setDefaultNightMode(it.toNightMode())
        }

        reviewViewModel.submitReview { reviewInfo, reviewManager ->
            reviewManager.launchReviewFlow(this, reviewInfo)
        }

        expandAppBarLayoutInPage()

        subscribeForInAppUpdate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            UPDATE_CONFIRMATION_REQ_CODE -> {
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

    private fun subscribeForInAppUpdate() {
        with(inAppUpdateViewModel) {
            updateStatus.observe(owner = this@MainActivity) { updateResult: AppUpdateResult ->
                updateUpdateButton(updateResult)

                // If it's an immediate update, launch it immediately and finish Activity
                // to prevent the user from using the app until they update.
                if (updateResult is AppUpdateResult.Available) {
                    if (shouldLaunchImmediateUpdate(updateResult.updateInfo)) {
                        if (appUpdateManager.startUpdateFlowForResult(
                                        updateResult.updateInfo,
                                        AppUpdateType.IMMEDIATE,
                                        this@MainActivity,
                                        UPDATE_CONFIRMATION_REQ_CODE)) {
                            finish()
                        }
                    }
                }
            }

            events.onEach { event ->
                when (event) {
                    is InAppUpdateEvent.ToastEvent -> toast(event.message)
                    is InAppUpdateEvent.StartUpdateEvent -> {
                        val updateType = if (event.immediate) AppUpdateType.IMMEDIATE else AppUpdateType.FLEXIBLE
                        appUpdateManager.startUpdateFlowForResult(
                                event.updateInfo,
                                updateType,
                                this@MainActivity,
                                UPDATE_CONFIRMATION_REQ_CODE
                        )
                    }
                }
            }.launchIn(activityLifecycle)
        }
    }

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

    private fun expandAppBarLayoutInPage() {
        supportFragmentManager.addFragmentOnAttachListener { _, fragment ->
            when (fragment) {
                is CatalogFeature, is AboutUsFeature, is PhotoReportsFeature, is SettingsFeature -> {
                    binding.appBarLayout.setExpanded(true)
                }
            }
        }
    }

    private fun updateUpdateButton(updateResult: AppUpdateResult) {
        when (updateResult) {
            AppUpdateResult.NotAvailable -> {
                logD("No update available")
                snackBar.dismiss()
            }
            is AppUpdateResult.Available -> with(snackBar) {
                setText(R.string.in_app_update_available)
                setAction(R.string.in_app_update_now) {
                    inAppUpdateViewModel.invokeUpdate()
                }
                show()
            }
            is AppUpdateResult.InProgress -> {
                with(snackBar) {
                    val updateProgress: Int = calculateUpdateProgress(updateResult)

                    setText(getString(R.string.in_app_update_downloading, updateProgress))
                    setAction(null) {}
                    show()
                }
            }
            is AppUpdateResult.Downloaded -> {
                with(snackBar) {
                    setText(R.string.in_app_update_download_finished)
                    setAction(R.string.in_app_update_complete) {
                        inAppUpdateViewModel.invokeUpdate()
                    }
                    show()
                }
            }
        }
    }

    private fun calculateUpdateProgress(updateResult: AppUpdateResult.InProgress): Int =
            when (updateResult.installState.totalBytesToDownload) {
                0L -> 0
                else -> {
                    (updateResult.installState.bytesDownloaded * 100 /
                            updateResult.installState.totalBytesToDownload).toInt()
                }
            }
}