package com.egoriku.ladyhappy.presentation.presenters.base

import com.egoriku.ladyhappy.presentation.ui.base.BaseMvpView

interface IBasePresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean

    fun checkViewAttached()
}