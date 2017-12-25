package com.egoriku.ladyhappy

import android.annotation.SuppressLint
import android.os.StrictMode
import timber.log.Timber


@SuppressLint("Registered")
open class DebugApplication : App() {

    override fun onCreate() {
        super.onCreate()
        DebugInitializer.register(this)
        Timber.plant(Timber.DebugTree())

        enableStrictMode()
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
    }
}

