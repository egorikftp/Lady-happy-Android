package com.egoriku.giugi.presentation.ui.activity

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.egoriku.corelib_kt.extensions.show
import com.egoriku.corelib_kt.listeners.SimpleAnimatorListener
import com.egoriku.giugi.App
import com.egoriku.giugi.R
import com.egoriku.giugi.common.Screens
import com.egoriku.giugi.mvp.start.StartActivityPresenter
import com.egoriku.giugi.mvp.start.StartActivityView
import kotlinx.android.synthetic.main.activity_start.*
import org.jetbrains.anko.intentFor
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class StartActivity : MvpAppCompatActivity(), StartActivityView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: StartActivityPresenter

    @ProvidePresenter
    fun createStartActivityPresenter() = StartActivityPresenter(router)

    private val navigator = object : SupportAppNavigator(this@StartActivity, R.id.activity_start_container) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = when (screenKey) {
            Screens.MAIN_ACTIVITY -> intentFor<MainActivity>()
            else -> null
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this@StartActivity)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)
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
                    presenter.openMainActivity()
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

    override fun onBackPressed() = presenter.onBackPressed()
}
