package com.egoriku.storage.di

import com.egoriku.core.di.RepositoryProvider
import dagger.Component

@Component(modules = [StorageModule::class])
interface StorageComponent : RepositoryProvider {

    class Initializer private constructor() {

        companion object {
            fun init(): RepositoryProvider = DaggerStorageComponent.create()
        }
    }
}