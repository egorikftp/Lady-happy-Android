package com.egoriku.ui.arch.pvm

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver

abstract class BasePresenter<V : BaseContract.View> : LifecycleObserver, BaseContract.Presenter<V> {

    override var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun attachLifecycle(lifecycle: Lifecycle) = lifecycle.addObserver(this)

    override fun detachLifecycle(lifecycle: Lifecycle) = lifecycle.removeObserver(this)

    override fun onPresenterDestroy() = Unit

    override fun onPresenterCreated() = Unit
}
