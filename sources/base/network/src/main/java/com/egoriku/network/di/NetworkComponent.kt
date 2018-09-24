package com.egoriku.network.di

import com.egoriku.core.di.MainToolsProvider
import dagger.Component

@Component(
        modules = [NetworkModule::class],
        dependencies = [MainToolsProvider::class])
interface NetworkComponent : NetworkProvider