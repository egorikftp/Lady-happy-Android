package com.egoriku.core

import android.content.Context
import com.egoriku.core.di.ApplicationProvider

interface IApplication {
    fun getAppComponent(): ApplicationProvider
    fun getApplicationContext(): Context
}