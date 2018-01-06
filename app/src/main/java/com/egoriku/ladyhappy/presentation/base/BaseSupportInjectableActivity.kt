package com.egoriku.ladyhappy.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.egoriku.corelib_kt.arch.BaseActivity
import com.egoriku.corelib_kt.arch.BaseContract
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseSupportInjectableActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : BaseActivity<V, P>(),
        HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
}