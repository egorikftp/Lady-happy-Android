package com.egoriku.landingfragment.presentation

import com.egoriku.core.model.ILandingModel
import com.egoriku.ui.arch.pvm.BaseContract
import com.egoriku.ui.arch.pvm.BaseView

internal interface LandingPageContract {

    interface View : BaseView {
        fun initViews()

        fun render(model: ILandingModel)
    }

    interface Presenter : BaseContract.Presenter<View>{

        fun loadLandingData()
    }
}