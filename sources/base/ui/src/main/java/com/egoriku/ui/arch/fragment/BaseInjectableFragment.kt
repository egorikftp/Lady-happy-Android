package com.egoriku.ui.arch.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egoriku.ui.arch.pvm.BaseContract
import com.egoriku.ladyhappy.extensions.inflate

abstract class BaseInjectableFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : BaseFragment<V, P>() {

    override fun onAttach(context: Context?) {
        injectDependencies()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(provideLayout())

    abstract fun injectDependencies()

    @LayoutRes
    abstract fun provideLayout(): Int
}