package com.egoriku.photoreportfragment.presentation

import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.model.IComplexPhotoReportModel
import com.egoriku.core.usecase.DefaultObserver
import com.egoriku.core.usecase.Params
import com.egoriku.photoreportfragment.domain.interactor.PhotoReportUseCase
import com.egoriku.ui.arch.pvm.BasePresenter
import javax.inject.Inject

class AllGoodsPresenter
@Inject constructor(
        private val photoReportUseCase: PhotoReportUseCase,
        private val analyticsHelper: IAnalyticsHelper
) : BasePresenter<AllGoodsContract.View>(), AllGoodsContract.Presenter {

    private var screenModel = ScreenModel()

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        analyticsHelper.trackPageView(TrackingConstants.FRAGMENT_ALL_GOODS)
    }

    override fun onPresenterDestroy() {
        photoReportUseCase.dispose()
        super.onPresenterDestroy()
    }

    override fun loadData() {
        if (isViewAttached) {
            view.showLoading()

            if (screenModel.isPhotoReportsEmpty()) {
                photoReportUseCase.execute(object : DefaultObserver<IComplexPhotoReportModel>() {
                    override fun onNext(t: IComplexPhotoReportModel) {
                        onGetPhotoReportSuccessTracking()
                        onGetPhotoReportSuccess(t)
                    }

                    override fun onError(exception: Throwable) {
                        onGetPhotoReportErrorTracking()
                        onGetPhotoReportError(exception)
                    }
                }, Params.EMPTY)
            } else {
                view.hideLoading()
                view.render(screenModel)
            }
        }
    }

    override fun onGetPhotoReportSuccess(newsModel: IComplexPhotoReportModel) {
        if (isViewAttached) {
            screenModel.photoReports = newsModel.photoReports

            view.hideLoading()
            view.render(screenModel)
        }
    }

    override fun onGetPhotoReportError(e: Throwable) {
        if (isViewAttached) {
            view.hideLoading()
        }
    }

    override fun onGetPhotoReportSuccessTracking() {
        analyticsHelper.trackGetCategoriesSuccess(null)
    }

    override fun onGetPhotoReportErrorTracking() {
        analyticsHelper.trackGetCategoriesFail(null)
    }
}