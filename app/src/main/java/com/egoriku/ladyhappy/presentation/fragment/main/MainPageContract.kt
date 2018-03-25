package com.egoriku.ladyhappy.presentation.fragment.main

import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.presentation.base.BaseView

interface MainPageContract {

    interface View : BaseView {
        fun initViews()
        fun showInformation()
    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}