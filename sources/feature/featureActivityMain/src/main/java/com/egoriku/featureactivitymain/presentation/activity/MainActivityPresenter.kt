package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IRouter
import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.featureactivitymain.common.Fragments
import com.egoriku.featureactivitymain.common.Screens
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract.View.Companion.ALL_GOODS_POSITION
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract.View.Companion.FEEDBACK_POSITION
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract.View.Companion.MAIN_PAGE_POSITION
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract.View.Companion.ORDER_POSITION
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract.View.Companion.SHARE_POSITION
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(private val router: IRouter, private val analyticsInterface: IAnalyticsHelper)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    companion object {
        const val DEFAULT_POSITION = -1
    }

    private val drawerData by lazy {
        mutableMapOf(
                ALL_GOODS_POSITION to Fragments.ALL_GOODS,
                ORDER_POSITION to Fragments.ORDER,
                SHARE_POSITION to Fragments.SHARE,
                FEEDBACK_POSITION to Fragments.FEEDBACK,
                MAIN_PAGE_POSITION to Fragments.MAIN_PAGE
        )
    }

    private var currentDrawerPosition = DEFAULT_POSITION

    override fun openMainPageFragment() {
        processNavigation(MAIN_PAGE_POSITION, TrackingConstants.FRAGMENT_MAIN_PAGE)
    }

    override fun openAllGoodsCategory() {
        processNavigation(ALL_GOODS_POSITION, TrackingConstants.DRAWER_ALL_GOODS)
    }

    override fun openOrderCategory() {
        processNavigation(ORDER_POSITION, TrackingConstants.DRAWER_ORDER)
    }

    override fun openShareCategory() {
        processNavigation(SHARE_POSITION, TrackingConstants.DRAWER_SHARE)
    }

    override fun openFeedbackCategory() {
        processNavigation(FEEDBACK_POSITION, TrackingConstants.DRAWER_FEEDBACK)
    }

    override fun openCreateNewPostScreen() {
        router.navigateTo(Screens.CREATE_POST_ACTIVITY)
    }

    private fun processNavigation(newDrawerPosition: Int, trackingConstant: String) {
        if (currentDrawerPosition != newDrawerPosition) {
            analyticsInterface.trackPageView(trackingConstant)
            currentDrawerPosition = newDrawerPosition

            if (newDrawerPosition == ALL_GOODS_POSITION) {
                router.newRootScreen(drawerData[newDrawerPosition])
            } else {
                router.navigateTo(drawerData[newDrawerPosition])
            }
        }
    }

    override fun onBackPressed() {
        router.exit()
    }
}