package com.egoriku.ladyhappy.presentation.presenters

import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.presentation.ui.base.BaseMvpView

interface LaunchMVP {

    interface View : BaseMvpView

    interface Presenter : BaseContract.Presenter<LaunchMVP.View> {
        fun processOpeningApp()
        fun onBackPressed()
    }
}