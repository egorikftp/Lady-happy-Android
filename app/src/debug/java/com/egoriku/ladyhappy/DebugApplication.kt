package com.egoriku.ladyhappy

import android.annotation.SuppressLint
import android.os.StrictMode
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.egoriku.ladyhappy.beagle.BeagleDebugMenuInitializer
import com.egoriku.ladyhappy.koin.debugModule
import com.egoriku.ladyhappy.leakcanary.LeakCanaryInitializer
import io.fabric.sdk.android.Fabric
import org.koin.core.context.loadKoinModules

@SuppressLint("Registered")
open class DebugApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModules(debugModule)

        enableStrictMode()
        setupCrashlytics()

        LeakCanaryInitializer().init()
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
                .detectNetwork()
                .penaltyLog()
                .penaltyDeathOnNetwork()
                .build())
    }
}