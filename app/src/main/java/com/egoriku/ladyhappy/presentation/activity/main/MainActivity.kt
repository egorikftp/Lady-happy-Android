package com.egoriku.ladyhappy.presentation.activity.main

import android.content.Intent
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
import com.egoriku.corelib_kt.arch.BaseActivity
import com.egoriku.corelib_kt.dsl.drawableCompat
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.di.activity.ActivityComponent
import com.egoriku.ladyhappy.di.activity.ActivityModule
import com.egoriku.ladyhappy.di.activity.DaggerActivityComponent
import com.egoriku.ladyhappy.presentation.activity.newpost.CreateNewPostActivity
import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsFragment
import com.egoriku.ladyhappy.presentation.fragment.order.OrderFragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityContract.View, MainActivityContract.Presenter>(), MainActivityContract.View {

    private lateinit var navigationDrawer: Drawer
    private lateinit var headerResult: AccountHeader

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var component: ActivityComponent

    private var drawerItemTag: String = com.egoriku.corelib_kt.Constants.EMPTY

    @Suppress("UNUSED_EXPRESSION")
    private val navigator = object : SupportAppNavigator(this, supportFragmentManager, R.id.mainActivityContainer) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? {
            return when (screenKey) {
                Screens.CREATE_POST_ACTIVITY -> intentFor<CreateNewPostActivity>()
                else -> null
            }
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? {
            return when (screenKey) {
                Fragments.ALL_GOODS -> AllGoodsFragment.newInstance()
                Fragments.ORDER -> OrderFragment.newInstance()
                else -> null
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMainActivity)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initNavigationDrawer(savedInstanceState)

        if (savedInstanceState == null) {
            presenter.openAllGoodsCategory()
        }
    }

    override fun injectDependencies() {
        component = DaggerActivityComponent.builder()
                .appComponent(App.instance.appComponent)
                .activityModule(ActivityModule())
                .build()

        component.inject(this)
    }

    override fun initPresenter() = mainActivityPresenter

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

    override fun showMessage(message: String) {
    }

    override fun showNoNetwork() {
    }

    fun setUpToolbar(@StringRes title: Int) {
        supportActionBar?.title = getString(title)
    }
}
