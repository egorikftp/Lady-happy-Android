package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.ui.arch.pvm.BaseContract
import com.egoriku.ui.arch.pvm.BaseView

interface MainActivityContract {

    interface View : BaseView

    interface Presenter : BaseContract.Presenter<View> {
        fun openLanding()

        fun openPhotoReport()

        fun onBackPressed()
    }
}