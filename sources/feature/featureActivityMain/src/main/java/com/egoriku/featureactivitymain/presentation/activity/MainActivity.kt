package com.egoriku.featureactivitymain.presentation.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.MenuItem
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.badgeable.secondaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.egoriku.core.IApplication
import com.egoriku.core.di.utils.INavigationHolder
import com.egoriku.corelib_kt.dsl.drawableCompat
import com.egoriku.featureactivitymain.R
import com.egoriku.featureactivitymain.common.Fragments
import com.egoriku.featureactivitymain.common.Screens
import com.egoriku.featureactivitymain.di.MainActivityComponent
import com.egoriku.ui.BaseInjectableActivity
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

@Suppress("MemberVisibilityCanPrivate")
class MainActivity : BaseInjectableActivity<MainActivityContract.View, MainActivityContract.Presenter>(), MainActivityContract.View {

    private lateinit var navigationDrawer: Drawer
    private lateinit var headerResult: AccountHeader

    @Inject
    lateinit var mainActivityPresenter: MainActivityContract.Presenter

    @Inject
    lateinit var navigatorHolder: INavigationHolder

    private var drawerItemTag: String = com.egoriku.corelib_kt.Constants.EMPTY

    @Suppress("UNUSED_EXPRESSION")
    private val navigator = object : SupportAppNavigator(this, supportFragmentManager, R.id.mainActivityContainer) {

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?) = when (screenKey) {
           // Screens.CREATE_POST_ACTIVITY -> null  intentFor<DetailCategoryActivity>()
            else -> null
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            //Fragments.ALL_GOODS -> AllGoodsFragment.newInstance()
            //Fragments.ORDER -> OrderFragment.newInstance()
            Fragments.MAIN_PAGE -> BlankFragment.newInstance("", "")
            else -> null
        }
    }

    override fun initPresenter() = mainActivityPresenter

    override fun provideLayout(): Int = R.layout.activity_main

    override fun injectDependencies() {
        MainActivityComponent.Initializer
                .init((applicationContext as IApplication).getAppComponent())
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbarMainActivity)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initNavigationDrawer(savedInstanceState)

        if (savedInstanceState == null) {
            presenter.openMainPageFragment()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        navigationDrawer.actionBarDrawerToggle.syncState()
    }

    private fun initNavigationDrawer(savedInstanceState: Bundle?) {
        navigationDrawer = drawer {
            savedInstance = savedInstanceState
            hasStableIds = true
            toolbar = this@MainActivity.toolbarMainActivity

            headerResult = accountHeader {
                background = R.drawable.header
                savedInstance = savedInstanceState
                translucentStatusBar = true
                selectionListEnabledForSingleProfile = false
            }

            primaryItem(R.string.navigation_drawer_all_goods) {
                iconDrawable = drawableCompat(R.drawable.ic_toys)!!
                tag = Fragments.ALL_GOODS
                iconTintingEnabled = true
            }

            primaryItem(R.string.navigation_drawer_order) {
                iconDrawable = drawableCompat(R.drawable.ic_payment)!!
                tag = Fragments.ORDER
                iconTintingEnabled = true
            }

            divider()

            footer {
                secondaryItem(R.string.navigation_drawer_create_new_post) {
                    iconDrawable = drawableCompat(R.drawable.ic_create_new_post)!!
                    tag = Screens.CREATE_POST_ACTIVITY
                }
            }

            onItemClick { _, _, drawerItem ->
                if (drawerItem.tag != null) {
                    drawerItemTag = drawerItem.tag.toString()
                }

                false
            }

            onClosed {
                when (drawerItemTag) {
                    Fragments.ALL_GOODS -> presenter.openAllGoodsCategory()
                    Fragments.ORDER -> presenter.openOrderCategory()
                    Fragments.SHARE -> presenter.openShareCategory()
                    Fragments.FEEDBACK -> presenter.openFeedbackCategory()
                    Screens.CREATE_POST_ACTIVITY -> presenter.openCreateNewPostScreen()
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

    override fun onBackPressed() {
        if (navigationDrawer.isDrawerOpen) {
            navigationDrawer.closeDrawer()
            return
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }

        presenter.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        navigationDrawer.actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem) = navigationDrawer.actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)

    override fun onSaveInstanceState(outState: Bundle?) {
        navigationDrawer.saveInstanceState(outState)
        headerResult.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    fun setUpToolbar(@StringRes title: Int) {
        supportActionBar?.title = getString(title)
    }
}
