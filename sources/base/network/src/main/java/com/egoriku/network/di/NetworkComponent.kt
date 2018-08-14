package com.egoriku.network.di

import com.egoriku.core.di.MainToolsProvider
import com.egoriku.network.landing.LandingDataSource
import dagger.Component

@Component(
        modules = [NetworkModule::class],
        dependencies = [MainToolsProvider::class])
interface NetworkComponent : NetworkProvider

interface NetworkProvider {

    fun provideLandingDataSource(): LandingDataSource
}