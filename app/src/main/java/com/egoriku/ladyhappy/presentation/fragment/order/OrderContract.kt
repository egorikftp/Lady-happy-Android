package com.egoriku.ladyhappy.presentation.fragment.order

import android.support.annotation.StringRes
import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ui.BaseView

interface OrderContract {

    interface View : BaseView {
        fun showTitle(@StringRes title: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onBackPressed()
    }
}