package com.egoriku.landing.presentation

import com.egoriku.ladyhappy.arch.pvm.BaseContract

interface LandingPageContract {

    interface View : BaseContract.View {
        fun initViews()

        fun render(screenModel: LandingScreenModel)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadLandingData()

        fun retryLoading()
    }
}