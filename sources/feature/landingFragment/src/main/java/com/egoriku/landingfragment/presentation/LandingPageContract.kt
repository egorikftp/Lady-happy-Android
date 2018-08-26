package com.egoriku.landingfragment.presentation

import com.egoriku.core.models.ILandingModel
import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ui.BaseView

internal interface LandingPageContract {

    interface View : BaseView {
        fun initViews()

        fun showInformation(model: ILandingModel)
    }

    interface Presenter : BaseContract.Presenter<View>{

        fun loadLandingData()
    }
}