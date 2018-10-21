package com.egoriku.storage.di

import com.egoriku.core.di.DependenciesProvider
import com.egoriku.core.di.RepositoryProvider
import com.egoriku.network.di.DaggerNetworkComponent
import com.egoriku.network.di.NetworkProvider
import dagger.Component

@Component(
        modules = [StorageModule::class],
        dependencies = [DependenciesProvider::class, NetworkProvider::class]
)
interface StorageComponent : RepositoryProvider {

    companion object {
        fun init(dependenciesProvider: DependenciesProvider): RepositoryProvider {
            val networkProvider = DaggerNetworkComponent.builder()
                    .dependenciesProvider(dependenciesProvider)
                    .build()

            return DaggerStorageComponent.builder()
                    .networkProvider(networkProvider)
                    .dependenciesProvider(dependenciesProvider)
                    .build()
        }
    }
}