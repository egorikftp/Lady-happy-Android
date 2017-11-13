package com.egoriku.giugi.presentation.ui.base

import android.content.Context

interface BaseView {

    fun injectDependencies()

    fun attachToPresenter()
    fun detachFromPresenter()

    fun onLandscape()
    fun onPortrait()

    fun showLoading()
    fun hideLoading()

    fun showMessage(message: String)
    fun showNoNetwork()

    fun getContext(): Context?

}