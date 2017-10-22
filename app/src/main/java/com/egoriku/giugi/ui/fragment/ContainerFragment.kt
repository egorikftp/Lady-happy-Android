package com.egoriku.giugi.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egoriku.corelib_kt.extensions.inflate
import com.egoriku.giugi.App
import com.egoriku.giugi.R
import com.egoriku.giugi.common.ExtraConstants
import com.egoriku.giugi.common.Fragments
import com.egoriku.giugi.common.Screens
import com.egoriku.giugi.navigation.BackButtonListener
import com.egoriku.giugi.navigation.LocalCiceroneHolder
import com.egoriku.giugi.navigation.RouterProvider
import com.egoriku.giugi.ui.activity.CreateNewPostActivity
import com.egoriku.giugi.ui.fragment.allgoods.AllGoodsFragment
import com.egoriku.giugi.ui.fragment.order.OrderFragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class ContainerFragment : Fragment(), RouterProvider, BackButtonListener {

    companion object {
        fun newInstance(name: String): ContainerFragment {
            val fragment = ContainerFragment()

            val bundle: Bundle = Bundle().apply {
                putString(ExtraConstants.EXTRA_FRAGMENT_CONTAINER_NAME, name)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private var navigator: Navigator? = null

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    private fun getContainerName(): String {
        return arguments.getString(ExtraConstants.EXTRA_FRAGMENT_CONTAINER_NAME)
    }

    private fun getCicerone(): Cicerone<Router>? {
        return ciceroneHolder.getCicerone(getContainerName())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            getCicerone()?.router?.replaceScreen(Fragments.ALL_GOODS)
        }
    }

    override fun onResume() {
        super.onResume()
        getCicerone()?.navigatorHolder?.setNavigator(getNavigator())
    }

    override fun onPause() {
        getCicerone()?.navigatorHolder?.removeNavigator()
        super.onPause()
    }

    private fun getNavigator(): Navigator? {
        if (navigator == null) {
            navigator = object : SupportAppNavigator(activity, childFragmentManager, R.id.fragment_container) {

                override fun createActivityIntent(screenKey: String?, data: Any?): Intent? {
                    return when (screenKey) {
                        Screens.CREATE_POST_ACTIVITY -> Intent(context, CreateNewPostActivity::class.java)
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

                override fun exit() {
                    (activity as RouterProvider).getNavigationRouter()?.exit()
                }
            }
        }

        return navigator
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.fragment_container)

        return if (fragment != null && fragment is BackButtonListener && fragment.onBackPressed()) {
            true
        } else {
            (activity as RouterProvider).getNavigationRouter()?.exit()
            true
        }
    }

    override fun getNavigationRouter(): Router? {
        return getCicerone()?.router
    }
}
