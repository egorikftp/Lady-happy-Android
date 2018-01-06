package com.egoriku.ladyhappy.presentation.base

import android.content.Context
import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.corelib_kt.arch.BaseFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseInjectableFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : BaseFragment<V, P>(){

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}