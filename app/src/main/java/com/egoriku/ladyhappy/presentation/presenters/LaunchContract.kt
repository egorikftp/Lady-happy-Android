package com.egoriku.ladyhappy.presentation.presenters

import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.presentation.ui.base.BaseView

interface LaunchContract {

    interface View : BaseView

    interface Presenter : BaseContract.Presenter<View> {
        fun processOpeningApp()

        fun onBackPressed()
    }
}