package com.egoriku.ladyhappy.presentation.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.badgeable.secondaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.egoriku.corelib_kt.extensions.drawableCompat
import com.egoriku.giugi.R
import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.mvp.main.DrawerNavigationPresenter
import com.egoriku.ladyhappy.mvp.main.DrawerNavigationView
import com.egoriku.ladyhappy.presentation.ui.fragments.AllGoodsFragment
import com.egoriku.ladyhappy.presentation.ui.fragments.order.OrderFragment
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), DrawerNavigationView {

    private var allGoodsFragment: Fragment? = null
    private var orderFragment: Fragment? = null

    private lateinit var navigationDrawer: Drawer

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: DrawerNavigationPresenter

    @ProvidePresenter
    fun createDrawerNavigationPresenter() = DrawerNavigationPresenter(router)

    @Suppress("UNUSED_EXPRESSION")
    private val navigator = Navigator { command ->
        when (command) {
            is Replace -> when (command.screenKey) {
                Fragments.ALL_GOODS -> supportFragmentManager.beginTransaction()
                        .detach(orderFragment)
                        .attach(allGoodsFragment)
                        .commitNow()
                Fragments.ORDER -> supportFragmentManager.beginTransaction()
                        .detach(allGoodsFragment)
                        .attach(orderFragment)
                        .commitNow()
                else -> null
            }
            is Forward -> when (command.screenKey) {
                Screens.CREATE_POST_ACTIVITY -> intentFor<CreateNewPostActivity>()
                else -> null
            }
            is Back -> finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMainActivity)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initNavigationDrawer(savedInstanceState)
        initContainers()
    }

    @Suppress("SENSELESS_COMPARISON")
    private fun initContainers() {
        val fm = supportFragmentManager
        allGoodsFragment = fm.findFragmentByTag(Fragments.ALL_GOODS)

        if (allGoodsFragment == null) {
            allGoodsFragment = AllGoodsFragment.newInstance()
            fm.beginTransaction()
                    .add(R.id.mainActivityContainer, allGoodsFragment, Fragments.ALL_GOODS)
                    .detach(allGoodsFragment).commitNow()
        }

        orderFragment = fm.findFragmentByTag(Fragments.ORDER)
        if (orderFragment == null) {
            orderFragment = OrderFragment.newInstance()
            fm.beginTransaction()
                    .add(R.id.mainActivityContainer, orderFragment, Fragments.ORDER)
                    .detach(orderFragment).commitNow()
        }
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
                    Fragments.ALL_GOODS -> presenter.openAllGoodsCategory()
                    Fragments.ORDER -> presenter.openOrderCategory()
                    Fragments.SHARE -> presenter.openShareCategory()
                    Fragments.FEEDBACK -> presenter.openFeedbackCategory()
                    Screens.CREATE_POST_ACTIVITY -> presenter.openCreateNewPostScreen()
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

    fun onFragmentStart(titleResId: String) {
        supportActionBar?.title = titleResId
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return navigationDrawer.actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun selectDrawerItem(position: Int) {
        navigationDrawer.setSelectionAtPosition(position, false)
    }
}
