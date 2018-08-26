package com.egoriku.ladyhappy.presentation.fragment.order

import android.support.annotation.StringRes
import com.egoriku.ui.arch.pvm.BaseContract
import com.egoriku.ui.arch.pvm.BaseView

interface OrderContract {

    interface View : BaseView {
        fun showTitle(@StringRes title: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onBackPressed()
    }
}