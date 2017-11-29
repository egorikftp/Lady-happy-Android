package com.egoriku.ladyhappy.presentation.presenters.impl

import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.adapter.ToysItem
import com.egoriku.ladyhappy.data.Toy
import com.egoriku.ladyhappy.domain.interactors.Params
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.presentation.presenters.AllGoodsMVP
import com.egoriku.ladyhappy.presentation.presenters.base.BasePresenter
import com.egoriku.ladyhappy.rx.DefaultObserver
import javax.inject.Inject

class AllGoodsPresenter
@Inject constructor(private val getCategoriesUseCase: CategoriesUseCase, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<AllGoodsMVP.View>(), AllGoodsMVP.Presenter {

    override fun attachView(view: AllGoodsMVP.View) {
        super.attachView(view)
        analyticsInterface.trackPageView(TrackingConstants.FRAGMENT_ALL_GOODS)
    }

    override fun detachView() {
        getCategoriesUseCase.dispose()
        super.detachView()
    }

    override fun getCategories() {
        view?.showLoading()
        getCategoriesUseCase.execute(GetCategoriesObserver(), Params.EMPTY)

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

    override fun onGetCategoriesSuccess(categoriesModel: CategoriesModel) {
        checkViewAttached()
        view?.hideLoading()
        if (categoriesModel.isEmpty()) {
         /*   if (!SharedPreferencesHelper.getBoolean(sharedPreferences,
                    SharedPreferencesHelper.KEY_USER_TASK_AT_LEAST_ONCE)) {
                view.showBucketEmptyFirstTime()
            } else {
                view.showBucketEmpty()
            }*/

            TODO("Show empty view")

        } else {
            view?.showCategories(categoriesModel.toList())
        }
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

    override fun onGetCategoriesSuccessTracking() {
        analyticsInterface.trackGetCategoriesSuccess(null)
    }

    override fun onGetCategoriesFailureTracking() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private inner class GetCategoriesObserver : DefaultObserver<CategoriesModel>() {

        override fun onComplete() {
        }

        override fun onError(exception: Throwable) {
            onGetCategoriesFailureTracking()
            onGetCategoriesFailure(exception)
        }

        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        override fun onNext(categoriesModel: CategoriesModel) {
            onGetCategoriesSuccessTracking()
            onGetCategoriesSuccess(categoriesModel)
        }
    }
}