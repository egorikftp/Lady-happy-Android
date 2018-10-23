package com.egoriku.ui.arch.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.egoriku.ui.arch.pvm.BaseContract

abstract class BaseInjectableActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : BaseActivity<V, P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(provideLayout())
    }

    abstract fun injectDependencies()

    @LayoutRes
    abstract fun provideLayout(): Int
}