package com.egoriku.core.di

import com.egoriku.core.IApplication
import com.egoriku.core.actions.ShowMainScreenAction

interface ApplicationProvider :
        MainToolsProvider,
        MainScreenActionsProvider


interface MainToolsProvider {
    fun provideContext(): IApplication
    fun provideFirebaseFirestore(): IFirebaseFirestore
    fun provideAnalyticsHelper(): IAnalyticsHelper
}

interface MainScreenActionsProvider {
    fun provideShowMainScreenAction(): ShowMainScreenAction
}