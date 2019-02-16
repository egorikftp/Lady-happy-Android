package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IRouter
import com.egoriku.featureactivitymain.common.Constants.Tracking
import com.egoriku.featureactivitymain.presentation.screen.LandingScreen
import com.egoriku.featureactivitymain.presentation.screen.PhotoReportScreen
import com.egoriku.ladyhappy.arch.pvm.BasePresenter
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(
        private val router: IRouter,
        private val analyticsInterface: IAnalytics)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    private var currentScreen: SupportAppScreen? = null

    override fun openLanding() {
        newRootScreen(LandingScreen(), Tracking.TRACKING_FRAGMENT_LANDING)
    }

    override fun openPhotoReport() {
        navigateTo(PhotoReportScreen(), Tracking.TRACKING_FRAGMENT_PHOTO_REPORT)
    }

    private fun navigateTo(screen: SupportAppScreen, trackPageId: String) {
        if (checkIfScreenNew(screen)) return

        analyticsInterface.trackPageView(trackPageId)
        router.navigateTo(screen)
        currentScreen = screen
    }

    private fun newRootScreen(screen: SupportAppScreen, trackPageId: String) {
        if (checkIfScreenNew(screen)) return

        analyticsInterface.trackPageView(trackPageId)
        router.newRootScreen(screen)
        currentScreen = screen
    }

    private fun checkIfScreenNew(newScreen: SupportAppScreen) = currentScreen == newScreen

    override fun onBackPressed() = router.exit()
}