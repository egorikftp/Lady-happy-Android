package com.egoriku.ladyhappy

import android.annotation.SuppressLint
import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

@SuppressLint("Registered")
open class DebugApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
        DebugInitializer.register(this)
        Timber.plant(Timber.DebugTree())

       // enableStrictMode()
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
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

