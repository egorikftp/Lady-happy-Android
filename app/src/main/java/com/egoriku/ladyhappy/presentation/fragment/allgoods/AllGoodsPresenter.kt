package com.egoriku.ladyhappy.presentation.fragment.allgoods

import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.ladyhappy.domain.interactors.base.Params
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.rx.DefaultObserver
import javax.inject.Inject

class AllGoodsPresenter
@Inject constructor(private val categoriesUseCase: CategoriesUseCase, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<AllGoodsContract.View>(), AllGoodsContract.Presenter {

    private var screenModel = AllGoodsScreenModel()

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        analyticsInterface.trackPageView(TrackingConstants.FRAGMENT_ALL_GOODS)
    }

    override fun onPresenterDestroy() {
        categoriesUseCase.dispose()
        super.onPresenterDestroy()
    }

    override fun loadData() {
        if (isViewAttached) {
            view.showLoading()

            if (!screenModel.hasCategories()) {
                categoriesUseCase.execute(CategoriesObserver(), Params.EMPTY)
            } else {
                view.hideLoading()
                view.render(screenModel)
            }
        }
    }

    override fun onGetCategoriesSuccess(categoriesModel: CategoriesModel) {
        if (isViewAttached) {
            screenModel.categories = categoriesModel.categories

            view.hideLoading()
            view.render(screenModel)
        }
    }

    override fun onGetCategoriesFailure(e: Throwable) {
        if (isViewAttached) {
            view.hideLoading()
        }
    }

    override fun onCategoryClickSuccessTracking() {

    }

    override fun onCategoryClickFailureTracking(e: Throwable) {

    }

    override fun onGetCategoriesSuccessTracking() {
        analyticsInterface.trackGetCategoriesSuccess(null)
    }

    override fun onGetCategoriesFailureTracking() {
        analyticsInterface.trackGetCategoriesFail(null)
    }

    private inner class CategoriesObserver : DefaultObserver<CategoriesModel>() {

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