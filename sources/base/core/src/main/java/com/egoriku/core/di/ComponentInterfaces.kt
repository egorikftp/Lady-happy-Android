package com.egoriku.core.di

import androidx.lifecycle.ViewModelProvider
import com.egoriku.core.IApplication
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.core.di.utils.INavigationHolder
import com.egoriku.core.di.utils.IRouter

interface ApplicationProvider : DependenciesProvider

interface DependenciesProvider {
    fun provideContext(): IApplication

    fun provideFirebaseFirestore(): IFirebaseFirestore

    fun provideAnalyticsHelper(): IAnalytics

    fun provideRouter(): IRouter

    fun provideNavigationHolder(): INavigationHolder

    fun provideViewModelProviderFactory(): ViewModelProvider.Factory
}