package com.egoriku.ladyhappy.presentation.activity.launch

import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.presentation.base.BaseView

interface LaunchContract {

    interface View : BaseView

    interface Presenter : BaseContract.Presenter<View> {
        fun processOpeningApp()

        fun onBackPressed()
    }
}