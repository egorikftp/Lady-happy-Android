package com.egoriku.ladyhappy.presentation.activity.launch

import android.os.Bundle
import com.egoriku.corelib_kt.arch.BaseActivity
import com.egoriku.corelib_kt.dsl.runDelayed
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.di.activity.ActivityComponent
import com.egoriku.ladyhappy.di.activity.ActivityModule
import com.egoriku.ladyhappy.di.activity.DaggerActivityComponent
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import com.egoriku.ladyhappy.common.Screens
import ru.terrakok.cicerone.commands.Replace
import com.egoriku.corelib_kt.timber.e
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import org.jetbrains.anko.startActivity

class LaunchActivity : BaseActivity<LaunchContract.View, LaunchContract.Presenter>(), LaunchContract.View {

    companion object {
        const val LAUNCH_DELAY = 1_000L
    }

    @Inject
    lateinit var launchPresenter: LaunchPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var component: ActivityComponent

    private val customNavigator = Navigator { command ->
        if (command is Replace) {
            when (command.screenKey) {
                Screens.MAIN_ACTIVITY -> {
                    startActivity<MainActivity>()
                    finish()
                }
            }
        } else {
            e("Illegal command for this screen: " + command.javaClass.simpleName)
        }
    }

    override fun initPresenter() = launchPresenter

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
        navigatorHolder.setNavigator(customNavigator)

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

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }

    override fun showNoNetwork() {
    }
}
