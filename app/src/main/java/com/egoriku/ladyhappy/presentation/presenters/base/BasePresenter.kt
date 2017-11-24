package com.egoriku.ladyhappy.presentation.presenters.base

import com.egoriku.ladyhappy.presentation.ui.base.BaseMvpView


public open class BasePresenter<T : BaseMvpView> : IBasePresenter<T> {

    protected var view: T? = null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isViewAttached(): Boolean = view != null

    override fun checkViewAttached() {
        if (!isViewAttached()) throw ViewNotAttachedException()
    }

    class ViewNotAttachedException : RuntimeException("Call Presenter.attachView(BaseView) before asking for data")
}