package com.egoriku.ladyhappy.presentation.presenters.impl

import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.ladyhappy.domain.interactors.Params
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.presentation.presenters.AllGoodsContract
import com.egoriku.ladyhappy.rx.DefaultObserver
import javax.inject.Inject

class AllGoodsPresenter
@Inject constructor(private val getCategoriesUseCase: CategoriesUseCase, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<AllGoodsContract.View>(), AllGoodsContract.Presenter {

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        analyticsInterface.trackPageView(TrackingConstants.FRAGMENT_ALL_GOODS)
    }

    override fun onPresenterDestroy() {
        getCategoriesUseCase.dispose()
        super.onPresenterDestroy()
    }

    override fun getCategories() {
        view?.showLoading()
        getCategoriesUseCase.execute(GetCategoriesObserver(), Params.EMPTY)
    }

    override fun onGetCategoriesSuccess(categoriesModel: CategoriesModel) {
        if (isViewAttached) {
            view?.hideLoading()

            if (!categoriesModel.isEmpty()) {
                view?.showCategories(categoriesModel.toList())
            }
        }
    }

    override fun onGetCategoriesFailure(e: Throwable) {
        if (isViewAttached) {
            view?.hideLoading()
        }
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
        analyticsInterface.trackGetCategoriesFail(null)
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