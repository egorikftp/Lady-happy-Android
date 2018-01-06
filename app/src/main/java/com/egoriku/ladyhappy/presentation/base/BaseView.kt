package com.egoriku.ladyhappy.presentation.base

import com.egoriku.corelib_kt.arch.BaseContract

interface BaseView : BaseContract.View {
    fun showLoading()
    fun hideLoading()
}