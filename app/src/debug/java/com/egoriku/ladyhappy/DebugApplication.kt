package com.egoriku.ladyhappy

import android.annotation.SuppressLint
import android.os.StrictMode
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric

@SuppressLint("Registered")
open class DebugApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        enableStrictMode()
        setupCrashlytics()

        BeagleDebugMenuInitializer().initWith(this)
    }

    private fun setupCrashlytics() {
        Fabric.with(this, Crashlytics.Builder().core(
                CrashlyticsCore.Builder()
                        .disabled(true)
                        .build()
        ).build())
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
    }
}