package com.egoriku.giugi

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView

interface HelloWorldView : MvpView {
    fun showMessage(@StringRes message: Int)
}
