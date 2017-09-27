package com.egoriku.giugi.mvp.start

import com.arellomobile.mvp.MvpPresenter
import com.egoriku.giugi.common.Screens
import ru.terrakok.cicerone.Router

class StartActivityPresenter(private val router: Router) : MvpPresenter<StartActivityView>() {

    fun openMainActivity() {
        router.newRootScreen(Screens.MAIN_ACTIVITY)
    }

    fun onBackPressed() {
        router.exit()
    }
}
