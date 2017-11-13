package com.egoriku.giugi

import android.app.Application
import com.egoriku.corelib_kt.extensions.DelegatesExt
import com.egoriku.giugi.di.AppComponent
import com.egoriku.giugi.di.module.AppModule


 open class App : Application() {

    companion object {
        @JvmStatic
        var instance: App by DelegatesExt.notNullSingleValue()
            private set
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

