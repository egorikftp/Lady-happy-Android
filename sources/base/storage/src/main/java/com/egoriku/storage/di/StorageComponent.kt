package com.egoriku.storage.di

import com.egoriku.core.di.MainToolsProvider
import com.egoriku.core.di.RepositoryProvider
import com.egoriku.network.di.DaggerNetworkComponent
import com.egoriku.network.di.NetworkProvider
import dagger.Component

@Component(
        modules = [StorageModule::class],
        dependencies = [MainToolsProvider::class, NetworkProvider::class]
)
interface StorageComponent : RepositoryProvider {

    class Initializer private constructor() {

        companion object {
            fun init(mainToolsProvider: MainToolsProvider): RepositoryProvider {
                val networkProvider = DaggerNetworkComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .build()

                return DaggerStorageComponent.builder()
                        .networkProvider(networkProvider)
                        .mainToolsProvider(mainToolsProvider)
                        .build()
            }
        }
    }
}