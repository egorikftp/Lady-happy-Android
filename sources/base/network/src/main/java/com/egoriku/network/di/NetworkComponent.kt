package com.egoriku.network.di

import com.egoriku.core.di.DependenciesProvider
import dagger.Component

@Component(
        modules = [NetworkModule::class],
        dependencies = [DependenciesProvider::class])
interface NetworkComponent : NetworkProvider