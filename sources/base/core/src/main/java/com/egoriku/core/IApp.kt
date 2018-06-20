package com.egoriku.core

import com.egoriku.core.di.ApplicationProvider

interface IApp {
    fun getAppComponent(): ApplicationProvider
}