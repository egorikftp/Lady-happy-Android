package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IRouter
import com.egoriku.featureactivitymain.common.Constants
import com.egoriku.featureactivitymain.common.Fragments
import com.egoriku.ui.arch.pvm.BasePresenter
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(
        private val router: IRouter,
        private val analyticsInterface: IAnalyticsHelper)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    private var currentScreen: String = Constants.EMPTY

    override fun openLanding() {
        processNavigation(Fragments.LANDING_PAGE, TrackingConstants.TRACKING_FRAGMENT_LANDING)
    }

    override fun openPhotoReport() {
        processNavigation(Fragments.PHOTO_REPORT, TrackingConstants.TRACKING_FRAGMENT_PHOTO_REPORT)
    }

    private fun processNavigation(newScreen: String, trackingConstant: String) {
        if (currentScreen == newScreen) {
            return
        }

        analyticsInterface.trackPageView(trackingConstant)

        if (newScreen == Fragments.LANDING_PAGE) {
            router.newRootScreen(newScreen)
        } else {
            router.navigateTo(newScreen)
        }

        currentScreen = newScreen
    }

    override fun onBackPressed() {
        router.exit()
    }
}