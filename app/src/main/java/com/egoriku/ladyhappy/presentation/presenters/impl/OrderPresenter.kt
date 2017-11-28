package com.egoriku.ladyhappy.presentation.presenters.impl

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.presentation.presenters.OrderMVP
import com.egoriku.ladyhappy.presentation.presenters.base.BasePresenter
import javax.inject.Inject

class OrderPresenter
@Inject constructor(private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<OrderMVP.View>(), OrderMVP.Presenter {

    override fun onBackPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attachView(view: OrderMVP.View) {
        super.attachView(view)
        analyticsInterface.trackPageView(TrackingConstants.FRAGMENT_ORDER)
    }
}