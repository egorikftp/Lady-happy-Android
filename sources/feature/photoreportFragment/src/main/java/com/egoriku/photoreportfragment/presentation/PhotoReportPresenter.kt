package com.egoriku.photoreportfragment.presentation

import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.model.IComplexPhotoReportModel
import com.egoriku.core.usecase.AppObserver
import com.egoriku.core.usecase.Params
import com.egoriku.photoreportfragment.domain.interactor.PhotoReportUseCase
import com.egoriku.ui.arch.pvm.BasePresenter
import javax.inject.Inject

class PhotoReportPresenter
@Inject constructor(
        private val photoReportUseCase: PhotoReportUseCase,
        private val analyticsHelper: IAnalyticsHelper
) : BasePresenter<PhotoReportContract.View>(), PhotoReportContract.Presenter {

    private var screenModel = ScreenModel()

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        analyticsHelper.trackPageView(TrackingConstants.TRACKING_FRAGMENT_LANDING)
    }

    override fun onPresenterDestroy() {
        photoReportUseCase.dispose()
        super.onPresenterDestroy()
    }

    override fun loadData() {
        view?.showLoading()
        if (screenModel.isPhotoReportsEmpty()) {
            photoReportUseCase.execute(object : AppObserver<IComplexPhotoReportModel>() {
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
            view?.let {
                it.hideLoading()
                it.render(screenModel)
            }
        }
    }

    override fun onGetPhotoReportSuccess(newsModel: IComplexPhotoReportModel) {
        screenModel.photoReports = newsModel.photoReports

        view?.let {
            it.hideLoading()
            it.render(screenModel)
        }
    }

    override fun onGetPhotoReportError(e: Throwable) {
        view?.hideLoading()
    }

    override fun onGetPhotoReportSuccessTracking() = analyticsHelper.trackGetCategoriesSuccess(null)

    override fun onGetPhotoReportErrorTracking() = analyticsHelper.trackGetCategoriesFail(null)
}