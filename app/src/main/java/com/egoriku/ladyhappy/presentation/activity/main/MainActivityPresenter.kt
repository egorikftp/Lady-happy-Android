package com.egoriku.ladyhappy.presentation.activity.main

import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract.View.Companion.ALL_GOODS_POSITION
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract.View.Companion.FEEDBACK_POSITION
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract.View.Companion.ORDER_POSITION
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract.View.Companion.SHARE_POSITION
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(private val router: Router, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    companion object {
        const val DEFAULT_POSITION = -1
    }

    private val drawerData by lazy {
        mutableMapOf(
                ALL_GOODS_POSITION to Fragments.ALL_GOODS,
                ORDER_POSITION to Fragments.ORDER,
                SHARE_POSITION to Fragments.SHARE,
                FEEDBACK_POSITION to Fragments.FEEDBACK
        )
    }

    private var currentDrawerPosition = DEFAULT_POSITION

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