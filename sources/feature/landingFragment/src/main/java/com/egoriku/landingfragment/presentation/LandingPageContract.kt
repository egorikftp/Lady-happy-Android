package com.egoriku.landingfragment.presentation

import com.egoriku.ui.arch.pvm.BaseContract

internal interface LandingPageContract {

    interface View : BaseContract.View {
        fun initViews()

        fun render(screenModel: LandingScreenModel)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadLandingData()

        fun retryLoading()
    }
}