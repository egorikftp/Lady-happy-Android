package com.egoriku.photoreportfragment.presentation

import com.egoriku.core.model.IComplexPhotoReportModel
import com.egoriku.ui.arch.pvm.BaseContract

interface PhotoReportContract {

    interface View : BaseContract.View {
        fun showLoading()

        fun hideLoading()

        fun render(screenModel: ScreenModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData()

        fun onGetPhotoReportError(e: Throwable)
        fun onGetPhotoReportSuccess(newsModel: IComplexPhotoReportModel)

        fun onGetPhotoReportSuccessTracking()
        fun onGetPhotoReportErrorTracking()
    }
}