package com.egoriku.mainscreen.presentation.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.egoriku.core.di.findDependencies
import com.egoriku.core.di.utils.INavigationHolder
import com.egoriku.ladyhappy.arch.activity.BaseActivity
import com.egoriku.ladyhappy.extensions.consume
import com.egoriku.ladyhappy.extensions.injectViewModel
import com.egoriku.ladyhappy.featureprovider.provider.FeatureScreen
import com.egoriku.mainscreen.R
import com.egoriku.mainscreen.di.MainActivityComponent
import com.egoriku.mainscreen.presentation.screen.LandingScreen
import com.egoriku.mainscreen.presentation.screen.PhotoReportScreen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_content.*
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigatorHolder: INavigationHolder

    private lateinit var viewModel: MainActivityViewModel

    private val navigator = object : SupportAppNavigator(this, R.id.container) {}

    override fun provideLayout(): Int = R.layout.activity_main

    override fun injectDependencies() = MainActivityComponent.init(findDependencies()).inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbarMainActivity)

        viewModel = injectViewModel(viewModelFactory)

        viewModel.screen.observe(this, Observer {
            headerBarLogoText.setText(it.pageTitle)
        })

        when (savedInstanceState) {
            null -> viewModel.navigateTo(LandingScreen())
            else -> with(savedInstanceState.getInt(KEY_SELECTED_MENU_ITEM)) {
                bottomNavigation.selectedItemId = this
            }
        }

        with(bottomNavigation) {
            setOnNavigationItemSelectedListener { item ->
                consume {
                    mapItemIdToScreen(item.itemId)
                }
            }

            setOnNavigationItemReselectedListener {}
        }

        settingsButton.setOnClickListener {
            FeatureScreen.getSettingsFragment().apply {
                show(supportFragmentManager, tag)
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }

        viewModel.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_SELECTED_MENU_ITEM, bottomNavigation.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    private fun mapItemIdToScreen(@IdRes menuItemId: Int) {
        when (menuItemId) {
            R.id.menuLanding -> viewModel.navigateTo(LandingScreen())
            R.id.menuPhotoReport -> viewModel.navigateTo(PhotoReportScreen())
        }
    }

    companion object {
        private const val KEY_SELECTED_MENU_ITEM = "selected_item"
    }
}