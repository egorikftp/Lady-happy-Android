package com.egoriku.ladyhappy.presentation.presenters.impl

import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.ALL_GOODS_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.CREATE_NEW_POST_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.FEEDBACK_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.ORDER_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.SHARE_POSITION
import com.egoriku.ladyhappy.presentation.presenters.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(private val router: Router, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<MainActivityMVP.View>(), MainActivityMVP.Presenter {

    private var currentFragment = ""

    override fun openAllGoodsCategory() {
        processNavigationForRoot(Fragments.ALL_GOODS, TrackingConstants.DRAWER_ALL_GOODS, ALL_GOODS_POSITION)
    }

    override fun openOrderCategory() {
        processNavigation(Fragments.ORDER, TrackingConstants.DRAWER_ORDER, ORDER_POSITION)
    }

    override fun openShareCategory() {
        processNavigation(Fragments.SHARE, TrackingConstants.DRAWER_SHARE, SHARE_POSITION)
    }

    override fun openFeedbackCategory() {
        processNavigation(Fragments.FEEDBACK, TrackingConstants.DRAWER_FEEDBACK, FEEDBACK_POSITION)
    }

    override fun openCreateNewPostScreen() {
        router.navigateTo(Screens.CREATE_POST_ACTIVITY)
        view?.selectDrawerItem(CREATE_NEW_POST_POSITION)
    }

    private fun processNavigationForRoot(fragmentToNavigate: String, trackingConstant: String, drawerPosition: Int) {
        if (currentFragment != fragmentToNavigate) {
            analyticsInterface.trackPageView(trackingConstant)
            router.newRootScreen(fragmentToNavigate)
            currentFragment = fragmentToNavigate
            view?.selectDrawerItem(drawerPosition)
        }
    }

    private fun processNavigation(fragmentToNavigate: String, trackingConstant: String, drawerPosition: Int) {
        if (currentFragment != fragmentToNavigate) {
            analyticsInterface.trackPageView(trackingConstant)
            router.navigateTo(fragmentToNavigate)
            currentFragment = fragmentToNavigate
            view?.selectDrawerItem(drawerPosition)
        }
    }

    override fun onBackPressed() {
        router.exit()
    }
}