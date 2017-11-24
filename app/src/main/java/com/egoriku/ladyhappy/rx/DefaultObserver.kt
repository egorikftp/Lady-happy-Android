package com.egoriku.ladyhappy.rx

import io.reactivex.observers.DisposableObserver

/**
 * Default [DisposableObserver] base class to be used whenever you want default error handling.
 */
open class DefaultObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {
        // Intentionally empty.
    }

    override fun onComplete() {
        // Intentionally empty.
    }

    override fun onError(exception: Throwable) {
        // Intentionally empty.
    }
}