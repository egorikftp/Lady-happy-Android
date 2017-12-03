package com.egoriku.ladyhappy.presentation.ui.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.MenuItem
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.badgeable.secondaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.egoriku.corelib_kt.extensions.drawableCompat
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.di.activity.ActivityComponent
import com.egoriku.ladyhappy.di.activity.ActivityModule
import com.egoriku.ladyhappy.di.activity.DaggerActivityComponent
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP
import com.egoriku.ladyhappy.presentation.presenters.impl.MainActivityPresenter
import com.egoriku.ladyhappy.presentation.ui.base.BaseActivity
import com.egoriku.ladyhappy.presentation.ui.fragments.AllGoodsFragment
import com.egoriku.ladyhappy.presentation.ui.fragments.OrderFragment
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject


class MainActivity : BaseActivity(), MainActivityMVP.View {

    private lateinit var navigationDrawer: Drawer

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    private lateinit var component: ActivityComponent

    @Suppress("UNUSED_EXPRESSION")
    private val navigator = object : SupportFragmentNavigator(supportFragmentManager, R.id.mainActivityContainer) {

        override fun createFragment(screenKey: String?, data: Any?): Fragment {
            return when (screenKey) {
                Fragments.ALL_GOODS -> AllGoodsFragment.newInstance()
                Fragments.ORDER -> OrderFragment.newInstance()
                else -> throw  IllegalStateException("Navigation to unknown screen")
            }
        }

        override fun exit() {
            finish()
        }

        override fun showSystemMessage(message: String?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        attachToPresenter()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMainActivity)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initNavigationDrawer(savedInstanceState)

        if (savedInstanceState == null) {
            mainActivityPresenter.openAllGoodsCategory()
        }
    }

    override fun injectDependencies() {
        component = DaggerActivityComponent.builder()
                .appComponent(App.instance.appComponent)
                .activityModule(ActivityModule())
                .build()

        component.inject(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        navigationDrawer.actionBarDrawerToggle.syncState()
    }

    private fun initNavigationDrawer(savedInstanceState: Bundle?) {
        navigationDrawer = drawer {
            savedInstance = savedInstanceState
            toolbar = this@MainActivity.toolbarMainActivity

            primaryItem(R.string.navigation_drawer_all_goods) {
                iconDrawable = drawableCompat(this@MainActivity, R.drawable.ic_toys)!!
                tag = Fragments.ALL_GOODS
            }

            primaryItem(R.string.navigation_drawer_order) {
                iconDrawable = drawableCompat(this@MainActivity, R.drawable.ic_payment)!!
                tag = Fragments.ORDER
            }

            divider()

            secondaryItem(R.string.navigation_drawer_share) {
                iconDrawable = drawableCompat(this@MainActivity, R.drawable.ic_tag_faces)!!
                tag = Fragments.SHARE
            }

            secondaryItem(R.string.navigation_drawer_feedback) {
                iconDrawable = drawableCompat(this@MainActivity, R.drawable.ic_favorite)!!
                tag = Fragments.FEEDBACK
            }

            footer {
                secondaryItem(R.string.navigation_drawer_create_new_post) {
                    iconDrawable = drawableCompat(this@MainActivity, R.drawable.ic_create_new_post)!!
                    tag = Screens.CREATE_POST_ACTIVITY
                }
            }

            onItemClick { _, _, drawerItem ->
                when (drawerItem.tag.toString()) {
                    Fragments.ALL_GOODS -> mainActivityPresenter.openAllGoodsCategory()
                    Fragments.ORDER -> mainActivityPresenter.openOrderCategory()
                    Fragments.SHARE -> mainActivityPresenter.openShareCategory()
                    Fragments.FEEDBACK -> mainActivityPresenter.openFeedbackCategory()
                    Screens.CREATE_POST_ACTIVITY -> mainActivityPresenter.openCreateNewPostScreen()
                }

                false
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
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

        mainActivityPresenter.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        navigationDrawer.actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem) = navigationDrawer.actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)

    override fun selectDrawerItem(position: Int) {
        navigationDrawer.setSelectionAtPosition(position, false)
    }

    override fun attachToPresenter() {
        mainActivityPresenter.attachView(this)
    }

    override fun detachFromPresenter() {
        mainActivityPresenter.detachView()
    }

    override fun onLandscape() {
    }

    override fun onPortrait() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getExtras(_intent: Intent) {
    }

    override fun showMessage(message: String) {
    }

    override fun showNoNetwork() {
    }

    override fun setUpToolbar(@StringRes title: Int) {
        supportActionBar?.title = getString(title)
    }

    override fun getAttachedFragment(id: Int): Fragment = supportFragmentManager.findFragmentById(id)

    override fun getAttachedFragment(tag: String): Fragment = supportFragmentManager.findFragmentByTag(tag)
}
