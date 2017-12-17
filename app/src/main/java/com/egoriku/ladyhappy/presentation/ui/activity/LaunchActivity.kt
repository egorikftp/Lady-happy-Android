package com.egoriku.ladyhappy.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.egoriku.corelib_kt.arch.BaseActivity
import com.egoriku.corelib_kt.extensions.runDelayed
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.di.activity.ActivityComponent
import com.egoriku.ladyhappy.di.activity.ActivityModule
import com.egoriku.ladyhappy.di.activity.DaggerActivityComponent
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.presenters.LaunchMVP
import com.egoriku.ladyhappy.presentation.presenters.impl.LaunchPresenter
import org.jetbrains.anko.intentFor
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class LaunchActivity : BaseActivity<LaunchMVP.View, LaunchMVP.Presenter>(), LaunchMVP.View {

    companion object {
        const val LAUNCH_DELAY = 1_000L
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var analyticsInterface: AnalyticsInterface

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var component: ActivityComponent

    override fun initPresenter(): LaunchMVP.Presenter {
        return LaunchPresenter(router, analyticsInterface)
    }

    private val navigator = object : SupportAppNavigator(this@LaunchActivity, R.id.activity_launch_container) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = when (screenKey) {
            Screens.MAIN_ACTIVITY -> intentFor<MainActivity>()
            else -> null
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }

    override fun injectDependencies() {
        component = DaggerActivityComponent.builder()
                .appComponent(App.instance.appComponent)
                .activityModule(ActivityModule())
                .build()

        component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)

        runDelayed(LAUNCH_DELAY) {
            presenter.processOpeningApp()
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        overridePendingTransition(0, 0)
        super.onPause()
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun attachToPresenter() {
        //presenter.attachView(this)
    }

    override fun detachFromPresenter() {
        //   presenter.detachView()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }

    override fun showNoNetwork() {
    }

    override fun getContext(): Context? = this
}
