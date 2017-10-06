package com.egoriku.giugi.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.egoriku.corelib_kt.extensions.drawableCompat
import com.egoriku.giugi.App
import com.egoriku.giugi.R
import com.egoriku.giugi.common.Fragments
import com.egoriku.giugi.mvp.main.DrawerNavigationPresenter
import com.egoriku.giugi.mvp.main.DrawerNavigationView
import com.egoriku.giugi.navigation.BackButtonListener
import com.egoriku.giugi.navigation.RouterProvider
import com.egoriku.giugi.ui.fragment.ContainerFragment
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), RouterProvider, DrawerNavigationView {
    private lateinit var navigationDrawer: Drawer

    private var allGoodsContainer: ContainerFragment? = null

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: DrawerNavigationPresenter

    @ProvidePresenter
    fun createDrawerNavigationPresenter() = DrawerNavigationPresenter(router)

    private val navigator = Navigator { command ->
        if (command is Back) {
            finish()
        } else if (command is Replace) {
            val fragmentManager = supportFragmentManager
            when (command.screenKey) {
                Fragments.ALL_GOODS -> fragmentManager.beginTransaction()
                        .attach(allGoodsContainer)
                        .commitNow()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initNavigationDrawer(savedInstanceState)
        initContainers()

        if (savedInstanceState == null) {
            //navigationDrawer.setSelectionAtPosition(1, true)
            presenter.onAllGoodsDrawerItemClick()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        navigationDrawer.actionBarDrawerToggle.syncState()
    }

    private fun initNavigationDrawer(savedInstanceState: Bundle?) {
        navigationDrawer = DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        PrimaryDrawerItem()
                                .withName(R.string.navigation_drawer_all_goods)
                                .withIcon(drawableCompat(this, R.drawable.ic_toys))
                                .withTag(Fragments.ALL_GOODS),
                        PrimaryDrawerItem()
                                .withName(R.string.navigation_drawer_order)
                                .withIcon(drawableCompat(this, R.drawable.ic_payment))
                                .withTag(Fragments.ORDER),
                        DividerDrawerItem(),
                        SecondaryDrawerItem()
                                .withName(R.string.navigation_drawer_share)
                                .withIcon(drawableCompat(this, R.drawable.ic_tag_faces))
                                .withTag(Fragments.SHARE),
                        SecondaryDrawerItem()
                                .withName(R.string.navigation_drawer_feedback)
                                .withIcon(drawableCompat(this, R.drawable.ic_favorite))
                                .withTag(Fragments.FEEDBACK))
                .withOnDrawerItemClickListener { _, _, drawerItem ->
                    when (drawerItem.tag.toString()) {
                        Fragments.ALL_GOODS -> presenter.onAllGoodsDrawerItemClick()
                        Fragments.ORDER -> presenter.onOrderDrawerItemClick()
                        Fragments.SHARE -> presenter.onShareDrawerItemClick()
                        Fragments.FEEDBACK -> presenter.onFeedbackDrawerItemClick()
                    }

                    true
                }
                //.withSavedInstance(savedInstanceState)
                .build()
    }

    private fun initContainers() {
        val fragmentManager = supportFragmentManager

        allGoodsContainer = fragmentManager.findFragmentByTag(Fragments.ALL_GOODS) as ContainerFragment?

        if (allGoodsContainer == null) {
            allGoodsContainer = ContainerFragment.newInstance(Fragments.ALL_GOODS)
            fragmentManager.beginTransaction()
                    .add(R.id.container, allGoodsContainer, Fragments.ALL_GOODS)
                    .detach(allGoodsContainer)
                    .commitNow()
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
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment != null && fragment is BackButtonListener && fragment.onBackPressed()) {
            return
        } else {
            presenter.onBackPressed()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        navigationDrawer.actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return navigationDrawer.actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun getNavigationRouter(): Router {
        return router
    }

    override fun selectDrawerItem(position: Int) {
        // navigationDrawer.setSelectionAtPosition(position)
    }
}
