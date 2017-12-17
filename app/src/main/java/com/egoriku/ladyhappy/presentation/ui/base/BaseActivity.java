package com.egoriku.ladyhappy.presentation.ui.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

import com.egoriku.corelib_kt.arch.BaseContract;

public abstract class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends com.egoriku.corelib_kt.arch.BaseActivity {

    public abstract void getExtras(Intent _intent);

    public abstract void setUpToolbar(@StringRes int title);

    public Context getContext() {
        return this;
    }
}
