package com.egoriku.core.di

import com.egoriku.core.IApplication
import com.egoriku.core.actions.ShowMainActivityAction
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.core.di.utils.INavigationHolder
import com.egoriku.core.di.utils.IRouter

interface ApplicationProvider :
        MainToolsProvider,
        MainScreenActionsProvider


interface MainToolsProvider {
    fun provideContext(): IApplication
    fun provideFirebaseFirestore(): IFirebaseFirestore
    fun provideAnalyticsHelper(): IAnalyticsHelper
    fun provideRouter(): IRouter
    fun provideNavigationHolder(): INavigationHolder
}

interface MainScreenActionsProvider {
    fun provideShowMainScreenAction(): ShowMainActivityAction
}