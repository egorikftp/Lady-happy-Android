package com.egoriku.ui.arch.pvm

import android.arch.lifecycle.Lifecycle

interface BaseContract {

    interface View

    interface Presenter<V : BaseContract.View> {

        val view: V?

        fun attachLifecycle(lifecycle: Lifecycle)
        fun detachLifecycle(lifecycle: Lifecycle)

        fun attachView(view: V)
        fun detachView()

        fun onPresenterCreated()
        fun onPresenterDestroy()
    }
}
