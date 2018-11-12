package com.egoriku.core.usecase

import io.reactivex.observers.DisposableObserver

/**
 * Default [DisposableObserver] base class to be used whenever you want default error handling.
 */
abstract class AppObserver<T> : DisposableObserver<T>() {
    override fun onNext(model: T) {}

    override fun onComplete() {}

    override fun onError(exception: Throwable) {}
}