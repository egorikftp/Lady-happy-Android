package com.egoriku.ui.arch.pvm;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;

public abstract class BasePresenter<V extends BaseContract.View> implements LifecycleObserver, BaseContract.Presenter<V> {

    private V view;

    @Override
    final public V getView() {
        return view;
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    final public void attachView(V view) {
        this.view = view;
    }

    @Override
    final public void detachView() {
        view = null;
    }

    @Override
    final public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void onPresenterDestroy() {
        //NO-OP
    }

    @Override
    public void onPresenterCreated() {
        //NO-OP
    }
}
