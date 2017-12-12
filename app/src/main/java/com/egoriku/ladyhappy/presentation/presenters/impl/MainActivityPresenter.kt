package com.egoriku.ladyhappy.presentation.presenters.impl

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.corelib_kt.timber.d
import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.external.TrackingConstants.Companion.DRAWER_ALL_GOODS
import com.egoriku.ladyhappy.external.TrackingConstants.Companion.DRAWER_FEEDBACK
import com.egoriku.ladyhappy.external.TrackingConstants.Companion.DRAWER_ORDER
import com.egoriku.ladyhappy.external.TrackingConstants.Companion.DRAWER_SHARE
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.ALL_GOODS_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.CREATE_NEW_POST_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.FEEDBACK_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.ORDER_POSITION
import com.egoriku.ladyhappy.presentation.presenters.MainActivityMVP.View.Companion.SHARE_POSITION
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(private val router: Router, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<MainActivityMVP.View>(), MainActivityMVP.Presenter {

    companion object {
        const val KEY_DRAWER_POSITION = "drawer_position"
    }

    private val drawerData = mutableMapOf(
            ALL_GOODS_POSITION to Fragments.ALL_GOODS,
            ORDER_POSITION to Fragments.ORDER,
            SHARE_POSITION to Fragments.SHARE,
            FEEDBACK_POSITION to Fragments.FEEDBACK
    )

    private var currentFragmentPosition = -1

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    protected fun onCreate() {
        Log.d("egorik", "onCreate")
        if (stateBundle.containsKey(KEY_DRAWER_POSITION)) {
            currentFragmentPosition = stateBundle.getInt(KEY_DRAWER_POSITION)
        }
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    protected fun onResume() {
        Log.d("egorik", "onResume")
        if (stateBundle.containsKey(KEY_DRAWER_POSITION)) {
            currentFragmentPosition = stateBundle.getInt(KEY_DRAWER_POSITION)
        }
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
        if (isViewAttached) {
            view.selectDrawerItem(CREATE_NEW_POST_POSITION)
        }
    }

    private fun processNavigation(newFragmentPosition: Int, trackingConstant: String) {
        Log.d("egorik", newFragmentPosition.toString() + " tr" + trackingConstant)
        if (currentFragmentPosition != newFragmentPosition) {
            analyticsInterface.trackPageView(trackingConstant)
            currentFragmentPosition = newFragmentPosition

            if (isViewAttached) {
                view.selectDrawerItem(newFragmentPosition)
                stateBundle.putInt(KEY_DRAWER_POSITION, newFragmentPosition)
            }

            if (newFragmentPosition == ALL_GOODS_POSITION) {
                router.newRootScreen(drawerData[newFragmentPosition])
            } else {
                router.navigateTo(drawerData[newFragmentPosition])
            }
        }
    }

    override fun onBackPressed() {
        router.exit()
    }
}