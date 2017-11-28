package com.egoriku.ladyhappy.presentation.presenters

import com.egoriku.ladyhappy.presentation.ui.base.BaseMvpView

interface LaunchMVP {

    interface View: BaseMvpView

    interface Presenter{

        fun processOpeningApp()
        fun onBackPressed()
    }
}