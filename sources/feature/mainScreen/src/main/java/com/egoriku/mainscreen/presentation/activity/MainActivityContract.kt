package com.egoriku.mainscreen.presentation.activity

import com.egoriku.ladyhappy.arch.pvm.BaseContract

interface MainActivityContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View> {
        fun openLanding()

        fun openPhotoReport()

        fun onBackPressed()
    }
}