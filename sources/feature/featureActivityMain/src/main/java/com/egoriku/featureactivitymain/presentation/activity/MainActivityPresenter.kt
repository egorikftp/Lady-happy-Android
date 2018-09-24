package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IRouter
import com.egoriku.featureactivitymain.common.Constants
import com.egoriku.ui.arch.pvm.BasePresenter
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(
        private val router: IRouter,
        private val analyticsInterface: IAnalyticsHelper)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    companion object {
        const val LANDING_PAGE = "LANDING_PAGE"
        const val PHOTO_REPORT = "PHOTO_REPORT"
    }

    private var currentScreen: String = Constants.EMPTY

    override fun openLanding() {
        processNavigation(LANDING_PAGE, TrackingConstants.TRACKING_FRAGMENT_LANDING)
    }

    override fun openPhotoReport() {
        processNavigation(PHOTO_REPORT, TrackingConstants.TRACKING_FRAGMENT_PHOTO_REPORT)
    }

    private fun processNavigation(newScreen: String, trackingConstant: String) {
        if (currentScreen == newScreen) {
            return
        }

        analyticsInterface.trackPageView(trackingConstant)

        if (newScreen == LANDING_PAGE) {
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