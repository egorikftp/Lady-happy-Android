package com.egoriku.ladyhappy.presentation.fragment.order

import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.ui.arch.pvm.BasePresenter
import javax.inject.Inject

class OrderPresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper)
    : BasePresenter<OrderContract.View>(), OrderContract.Presenter {

    override fun onBackPressed() {
    }

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        analyticsHelper.trackPageView(TrackingConstants.FRAGMENT_ORDER)
    }
}