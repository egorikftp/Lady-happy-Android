package com.egoriku.ladyhappy

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.egoriku.core.IApp
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.ladyhappy.di.app.AppComponent

open class App : Application(), IApp {

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this@App) }

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

