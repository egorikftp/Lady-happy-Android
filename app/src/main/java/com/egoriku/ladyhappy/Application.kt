package com.egoriku.ladyhappy

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.ladyhappy.di.application.AppComponent

open class Application : Application(), IApplication {

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this@Application) }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }

    override fun getAppComponent(): ApplicationProvider = appComponent
}

