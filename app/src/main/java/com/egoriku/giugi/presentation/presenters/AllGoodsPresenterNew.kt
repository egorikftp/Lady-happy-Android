package com.egoriku.giugi.presentation.presenters

import com.egoriku.giugi.R
import com.egoriku.giugi.adapter.ToysItem
import com.egoriku.giugi.data.Toy
import com.egoriku.giugi.external.AnalyticsInterface
import com.egoriku.giugi.external.TrackingConstants
import com.egoriku.giugi.presentation.presenters.base.BasePresenter
import javax.inject.Inject

class AllGoodsPresenterNew @Inject constructor(private val analyticsInterface: AnalyticsInterface) : BasePresenter<AllGoodsMVP.View>(), AllGoodsMVP.Presenter {

    override fun attachView(view: AllGoodsMVP.View) {
        super.attachView(view)
        analyticsInterface.trackPageView(TrackingConstants.VIEW_ALL_GOODS)
    }

    override fun getCategories() {
        view?.showLoading()

        val list = mutableListOf<Any>()
        list.add(ToysItem(Toy("1", R.drawable.ic1)))
        list.add(ToysItem(Toy("2", R.drawable.ic2)))
        list.add(ToysItem(Toy("3", R.drawable.ic3)))
        list.add(ToysItem(Toy("4", R.drawable.ic4)))
        list.add(ToysItem(Toy("5", R.drawable.ic5)))
        list.add(ToysItem(Toy("6", R.drawable.ic6)))
        list.add(ToysItem(Toy("7", R.drawable.ic7)))
        list.add(ToysItem(Toy("8", R.drawable.ic8)))
    }

    override fun onGetCategoriesSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetCategoriesFailure(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCategoryClickSuccessTracking() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCategoryClickFailureTracking(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}