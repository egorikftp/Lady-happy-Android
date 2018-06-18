package com.egoriku.core

import android.content.Context
import com.egoriku.core.di.ApplicationProvider

interface IApp {
    fun getApplicationContext(): Context
}