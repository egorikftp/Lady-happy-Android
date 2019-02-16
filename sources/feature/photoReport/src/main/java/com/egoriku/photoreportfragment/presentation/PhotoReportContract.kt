package com.egoriku.photoreportfragment.presentation

import com.egoriku.ladyhappy.arch.pvm.BaseContract

interface PhotoReportContract {

    interface View : BaseContract.View {
        fun showLoading()

        fun hideLoading()

        fun render(screenModel: ScreenModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData()
    }
}