package com.egoriku.ladyhappy.presentation.ui.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

public abstract class BaseActivity extends com.egoriku.corelib_kt.arch.BaseActivity {

    public abstract void getExtras(Intent _intent);

    public abstract void setUpToolbar(@StringRes int title);

    public Context getContext() {
        return this;
    }
}
