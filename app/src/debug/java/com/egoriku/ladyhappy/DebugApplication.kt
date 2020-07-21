package com.egoriku.ladyhappy

import android.annotation.SuppressLint
import android.os.StrictMode
import com.egoriku.ladyhappy.beagle.BeagleDebugMenuInitializer
import com.egoriku.ladyhappy.koin.debugModule
import com.egoriku.ladyhappy.leakcanary.LeakCanaryInitializer
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.core.context.loadKoinModules

@SuppressLint("Registered")
open class DebugApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModules(debugModule)

        enableStrictMode()
        setupFirebaseCrashlytics()

        LeakCanaryInitializer().init()
        BeagleDebugMenuInitializer().initWith(this)
    }

    private fun setupFirebaseCrashlytics() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
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