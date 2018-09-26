package com.egoriku.landingfragment.presentation

import com.egoriku.core.model.ILandingModel
import com.egoriku.ui.arch.pvm.BaseContract

internal interface LandingPageContract {

    interface View : BaseContract.View {
        fun initViews()

        fun showLoading()

        fun hideLoading()

        fun render(model: ILandingModel)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadLandingData()
    }
}