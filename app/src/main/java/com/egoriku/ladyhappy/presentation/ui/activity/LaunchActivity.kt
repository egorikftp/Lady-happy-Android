package com.egoriku.ladyhappy.presentation.ui.activity

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.egoriku.corelib_kt.extensions.show
import com.egoriku.corelib_kt.listeners.SimpleAnimatorListener
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.di.activity.ActivityComponent
import com.egoriku.ladyhappy.di.activity.ActivityModule
import com.egoriku.ladyhappy.di.activity.DaggerActivityComponent
import com.egoriku.ladyhappy.presentation.presenters.LaunchMVP
import com.egoriku.ladyhappy.presentation.presenters.impl.LaunchPresenter
import kotlinx.android.synthetic.main.activity_start.*
import org.jetbrains.anko.intentFor
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class LaunchActivity : AppCompatActivity(), LaunchMVP.View {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var launchPresenter: LaunchPresenter

    private lateinit var component: ActivityComponent

    private val navigator = object : SupportAppNavigator(this@LaunchActivity, R.id.activity_start_container) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = when (screenKey) {
            Screens.MAIN_ACTIVITY -> intentFor<MainActivity>()
            else -> null
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        attachToPresenter()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
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

        startActivityImageView.apply {
            addAnimatorListener(object : SimpleAnimatorListener() {
                override fun onAnimationStart(p0: Animator?) {
                    startActivityLogoText.show()
                }

                override fun onAnimationEnd(p0: Animator?) {
                    launchPresenter.processOpeningApp()
                }
            })
            playAnimation()
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        overridePendingTransition(0, 0)
        super.onPause()
    }

    override fun onDestroy() {
        detachFromPresenter()
        super.onDestroy()
    }

    override fun onBackPressed() = launchPresenter.onBackPressed()

    override fun attachToPresenter() {
        launchPresenter.attachView(this)
    }

    override fun detachFromPresenter() {
        launchPresenter.detachView()
    }

    override fun onLandscape() {
    }

    override fun onPortrait() {
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
