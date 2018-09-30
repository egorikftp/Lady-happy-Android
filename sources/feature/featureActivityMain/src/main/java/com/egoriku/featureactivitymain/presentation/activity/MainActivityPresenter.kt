package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IRouter
import com.egoriku.featureactivitymain.presentation.screen.LandingScreen
import com.egoriku.featureactivitymain.presentation.screen.PhotoReportScreen
import com.egoriku.featureactivitymain.presentation.screen.ScreenFactory
import com.egoriku.ui.arch.pvm.BasePresenter
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(
        private val router: IRouter,
        private val analyticsInterface: IAnalyticsHelper,
        private val screenFactory: ScreenFactory)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    private var currentScreen: SupportAppScreen? = null

    override fun openLanding() {
        newRootScreen(LandingScreen(screenFactory.getLanding()), TrackingConstants.TRACKING_FRAGMENT_LANDING)
    }

    override fun openPhotoReport() {
        navigateTo(PhotoReportScreen(screenFactory.getPhotoReport()), TrackingConstants.TRACKING_FRAGMENT_PHOTO_REPORT)
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