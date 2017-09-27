package com.egoriku.giugi

import com.egoriku.corelib_kt.extensions.DelegatesExt
import com.egoriku.giugi.dagger.AppComponent
import com.egoriku.giugi.dagger.DaggerAppComponent


class App : DebugApplication() {

    companion object {
        @JvmStatic
        var instance: App by DelegatesExt.notNullSingleValue()
            private set
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

