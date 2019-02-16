package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.ui.arch.pvm.BaseContract

interface MainActivityContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View> {
        fun openLanding()

        fun openPhotoReport()

        fun onBackPressed()
    }
}