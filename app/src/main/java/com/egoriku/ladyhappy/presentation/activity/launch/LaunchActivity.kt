package com.egoriku.ladyhappy.presentation.activity.launch

import android.os.Bundle
import com.egoriku.corelib_kt.arch.BaseActivity
import com.egoriku.corelib_kt.dsl.runDelayed
import com.egoriku.corelib_kt.timber.e
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import dagger.android.AndroidInjection
import org.jetbrains.anko.startActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class LaunchActivity : BaseActivity<LaunchContract.View, LaunchContract.Presenter>(), LaunchContract.View {

    companion object {
        const val LAUNCH_DELAY = 1_000L
    }

    @Inject
    lateinit var launchPresenter: LaunchContract.Presenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val customNavigator = Navigator { commands: Array<out Command> ->
        for (command in commands) applyCommand(command)
    }

    private fun applyCommand(command: Command) {
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
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
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
}
