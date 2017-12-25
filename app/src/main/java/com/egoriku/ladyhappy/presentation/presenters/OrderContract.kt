package com.egoriku.ladyhappy.presentation.presenters

import android.support.annotation.StringRes
import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.presentation.ui.base.BaseView

interface OrderContract {

    interface View : BaseView {
        fun showTitle(@StringRes title: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onBackPressed()
    }
}