package com.egoriku.ladyhappy

import android.app.Application
import com.egoriku.corelib_kt.dsl.DelegatesDsl
import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.app.AppModule
import com.egoriku.ladyhappy.di.app.DaggerAppComponent

open class App : Application() {

    companion object {
        @JvmStatic
        var instance: App by DelegatesDsl.notNullSingleValue()
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

