package com.egoriku.giugi

import android.annotation.SuppressLint
import android.support.multidex.MultiDexApplication

@SuppressLint("Registered")
open class DebugApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DebugInitializer.register(this)
    }
}

