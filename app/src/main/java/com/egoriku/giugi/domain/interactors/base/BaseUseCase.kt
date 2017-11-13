package com.egoriku.giugi.domain.interactors.base

import com.egoriku.giugi.domain.interactors.Params
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.observers.DisposableObserver

/**
 * Abstract class for a UseCase/Interactor
 * Will execute its job in a background thread and will post the result in the UI thread.
 * Will return the result using a {@link Disposable}
 */

abstract class BaseUseCase {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun getObservable(params: Params): Observable<*>

    /**
     * Executes the current UseCase.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * with [.getObservable].
     */

    fun execute(observer: DisposableObserver<in Any>, params: Params) {
        val observable = this.getObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable?) {
        if (disposable != null) {
            disposables.add(disposable)
        }
    }
}