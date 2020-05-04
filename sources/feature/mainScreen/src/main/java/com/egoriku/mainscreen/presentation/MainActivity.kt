package com.egoriku.mainscreen.presentation

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.connector.IDynamicFeatureConnector
import com.egoriku.core.di.findDependencies
import com.egoriku.core.di.utils.INavigationHolder
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.arch.activity.BaseActivity
import com.egoriku.ladyhappy.extensions.*
import com.egoriku.ladyhappy.navigation.navigator.platform.ActivityScopeNavigator
import com.egoriku.mainscreen.R
import com.egoriku.mainscreen.databinding.ActivityMainBinding
import com.egoriku.mainscreen.di.MainActivityComponent
import com.egoriku.mainscreen.presentation.dynamicfeature.DynamicFeatureViewModel
import com.egoriku.mainscreen.presentation.screen.*
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main), IDynamicFeatureConnector {

    private val binding: ActivityMainBinding by viewBinding(R.id.contentFullScreen)

    private val featureProvider: IFeatureProvider by inject()
    private val navigatorHolder: INavigationHolder by inject()
    private val dynamicFeatureViewModel: DynamicFeatureViewModel by viewModel()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainActivityViewModel

    private val navigator = ActivityScopeNavigator(this, R.id.container)

    override fun injectDependencies() = MainActivityComponent.init(findDependencies()).inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!hasM()) {
            window.statusBarColor = Color.BLACK
        }

        setSupportActionBar(binding.toolbarMainActivity)

        viewModel = injectViewModel(viewModelFactory)

        viewModel.screenTitle.observe(this, Observer {
            binding.toolbarContent.headerBarLogoText.setText(it)
        })

        when (savedInstanceState) {
            null -> viewModel.replaceWith(CatalogScreen(featureProvider))
            else -> with(savedInstanceState.getInt(KEY_SELECTED_MENU_ITEM)) {
                binding.bottomNavigation.selectedItemId = this
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

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

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

    companion object {
        private const val KEY_SELECTED_MENU_ITEM = "selected_item"
    }
}