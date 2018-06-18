package com.egoriku.core.di

import com.egoriku.core.IApp

interface ApplicationProvider : MainToolsProvider


interface MainToolsProvider {
    fun provideContext(): IApp
}