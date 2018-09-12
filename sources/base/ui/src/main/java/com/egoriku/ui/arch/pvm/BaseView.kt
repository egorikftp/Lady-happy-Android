package com.egoriku.ui.arch.pvm

@Deprecated("Move loading state into feature representation")
interface BaseView : BaseContract.View {
    fun showLoading()
    fun hideLoading()
}