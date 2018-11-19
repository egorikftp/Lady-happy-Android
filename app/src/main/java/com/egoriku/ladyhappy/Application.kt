package com.egoriku.ladyhappy

import android.app.Application
import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.ladyhappy.di.AppComponent

open class Application : Application(), IApplication {

    private val appComponent: AppComponent by lazy { AppComponent.init(this@Application) }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun getAppComponent(): ApplicationProvider = appComponent
}

