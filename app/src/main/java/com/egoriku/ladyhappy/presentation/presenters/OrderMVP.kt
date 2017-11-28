package com.egoriku.ladyhappy.presentation.presenters

import android.support.annotation.StringRes
import com.egoriku.ladyhappy.presentation.ui.base.BaseMvpView

interface OrderMVP {

    interface View : BaseMvpView {
        fun showTitle(@StringRes title: Int)
    }

    interface Presenter {
        fun onBackPressed()
    }
}