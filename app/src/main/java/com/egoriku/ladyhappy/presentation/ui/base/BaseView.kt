package com.egoriku.ladyhappy.presentation.ui.base

import android.content.Context
import com.egoriku.corelib_kt.arch.BaseContract

interface BaseView : BaseContract.View {

    fun injectDependencies()

    @Deprecated("")
    fun attachToPresenter()
    @Deprecated("")
    fun detachFromPresenter()

    fun showLoading()
    fun hideLoading()

    fun showMessage(message: String)
    fun showNoNetwork()

    fun getContext(): Context?

}