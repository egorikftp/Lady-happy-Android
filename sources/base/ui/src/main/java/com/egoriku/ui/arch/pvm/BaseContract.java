package com.egoriku.ui.arch.pvm;

import android.arch.lifecycle.Lifecycle;

public interface BaseContract {

    interface View {
    }

    interface Presenter<V extends BaseContract.View> {

        void attachLifecycle(Lifecycle lifecycle);
        void detachLifecycle(Lifecycle lifecycle);

        void attachView(V view);
        void detachView();

        V getView();

        boolean isViewAttached();

        void onPresenterCreated();
        void onPresenterDestroy();
    }
}
