package com.egoriku.ladyhappy.presentation.presenters.impl

import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP
import com.egoriku.ladyhappy.presentation.presenters.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(private val router: Router, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<MainActivityMVP.View>(), MainActivityMVP.Presenter {

    override fun openAllGoodsCategory() {
        analyticsInterface.trackPageView(TrackingConstants.DRAWER_ALL_GOODS)
        router.replaceScreen(Fragments.ALL_GOODS)
    }

    override fun openOrderCategory() {
        analyticsInterface.trackPageView(TrackingConstants.DRAWER_ORDER)
        router.replaceScreen(Fragments.ORDER)
    }

    override fun openShareCategory() {
        analyticsInterface.trackPageView(TrackingConstants.DRAWER_SHARE)
        router.navigateTo(Fragments.SHARE)
    }

    override fun openFeedbackCategory() {
        analyticsInterface.trackPageView(TrackingConstants.DRAWER_FEEDBACK)
        router.navigateTo(Fragments.FEEDBACK)
    }

    override fun openCreateNewPostScreen() {
        router.navigateTo(Screens.CREATE_POST_ACTIVITY)
    }

    override fun onBackPressed() {
        router.exit()
    }
}