package com.egoriku.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.egoriku.corelib_kt.arch.BaseActivity
import com.egoriku.corelib_kt.arch.BaseContract

abstract class BaseSupportInjectableActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : BaseActivity<V, P>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(provideLayout())
    }

    abstract fun injectDependencies()

    @LayoutRes
    abstract fun provideLayout(): Int
}