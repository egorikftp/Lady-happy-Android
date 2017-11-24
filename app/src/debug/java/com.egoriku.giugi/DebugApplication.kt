package com.egoriku.giugi

import android.annotation.SuppressLint
import android.os.StrictMode
import com.egoriku.ladyhappy.App


@SuppressLint("Registered")
open class DebugApplication : App() {

    override fun onCreate() {
        super.onCreate()
        DebugInitializer.register(this)

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

