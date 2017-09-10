package com.egoriku.giugi

import kotlin.properties.ReadWriteProperty

class App : DebugApplication() {
    companion object {
        @JvmStatic
        var instance: App by DelegatesExt.notNullSingleValue()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

object DelegatesExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}

