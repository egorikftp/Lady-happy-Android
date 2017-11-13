package com.egoriku.giugi.presentation.presenters

import com.egoriku.giugi.presentation.ui.base.BaseMvpView

interface IBasePresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean

    fun checkViewAttached()
}