package com.egoriku.ladyhappy.presentation.fragment.order

import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import javax.inject.Inject

class OrderPresenter
@Inject constructor(private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<OrderContract.View>(), OrderContract.Presenter {

    override fun onBackPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        analyticsInterface.trackPageView(TrackingConstants.FRAGMENT_ORDER)
    }
}