package com.egoriku.ladyhappy

import android.annotation.SuppressLint
import android.os.StrictMode

@SuppressLint("Registered")
open class DebugApplication : Application() {

    override fun onCreate() {
        super.onCreate()
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

