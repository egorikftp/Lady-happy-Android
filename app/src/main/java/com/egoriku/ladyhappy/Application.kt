package com.egoriku.ladyhappy

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.ladyhappy.di.AppComponent
import com.egoriku.ladyhappy.koin.initKoin
import com.google.android.play.core.splitcompat.SplitCompatApplication

open class Application : SplitCompatApplication(), IApplication {

    private val appComponent: AppComponent by lazy { AppComponent.init(this@Application) }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        initKoin()

        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
    }

    override fun getAppComponent(): ApplicationProvider = appComponent
}

