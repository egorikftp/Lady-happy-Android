package com.egoriku.ladyhappy.presentation.base

import com.egoriku.corelib_kt.arch.BaseContract

interface BaseView : BaseContract.View {

    fun injectDependencies()

    fun showLoading()
    fun hideLoading()

    fun showMessage(message: String)
    fun showNoNetwork()
}