package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.core.actions.IPhotoReportFragmentAction
import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IRouter
import com.egoriku.featureactivitymain.presentation.screen.LandingScreen
import com.egoriku.featureactivitymain.presentation.screen.PhotoReportScreen
import com.egoriku.ui.arch.pvm.BasePresenter
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(
        private val router: IRouter,
        private val analyticsInterface: IAnalyticsHelper,
        private val landingFragmentAction: ILandingFragmentAction,
        private val photoReportFragmentAction: IPhotoReportFragmentAction)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    private var currentScreen: SupportAppScreen? = null

    override fun openLanding() {
        processNavigation(LandingScreen(landingFragmentAction), TrackingConstants.TRACKING_FRAGMENT_LANDING)
    }

    override fun openPhotoReport() {
        processNavigation(PhotoReportScreen(photoReportFragmentAction), TrackingConstants.TRACKING_FRAGMENT_PHOTO_REPORT)
    }

    private fun processNavigation(newScreen: SupportAppScreen, trackingConstant: String) {
        if (currentScreen == newScreen) {
            return
        }

        analyticsInterface.trackPageView(trackingConstant)

        router.navigateTo(newScreen)

        /*if (newScreen.screenKey == LANDING_PAGE) {
            router.newRootScreen(newScreen)
        } else {
            router.navigateTo(newScreen)
        }*/

        currentScreen = newScreen
    }

    override fun onBackPressed() {
        router.exit()
    }
}