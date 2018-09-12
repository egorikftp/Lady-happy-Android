package com.egoriku.photoreportfragment.presentation

import android.support.annotation.StringRes
import com.egoriku.core.model.IComplexPhotoReportModel
import com.egoriku.ui.arch.pvm.BaseContract
import com.egoriku.ui.arch.pvm.BaseView

interface AllGoodsContract {

    interface View : BaseView {
        fun showTitle(@StringRes title: Int)

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