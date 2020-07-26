package com.egoriku.ladyhappy

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import com.egoriku.core.IRemoteConfig
import com.egoriku.ladyhappy.koin.initKoin
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.android.inject

open class Application : SplitCompatApplication() {

    private val remoteConfig: IRemoteConfig by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()

        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)

        remoteConfig.fetch()
    }
}

