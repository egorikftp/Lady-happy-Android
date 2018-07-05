package com.egoriku.mainfragment.di

import com.egoriku.core.di.MainFragmentProvider
import com.egoriku.core.di.MainToolsProvider
import com.egoriku.mainfragment.di.module.MainFragmentExportModule
import dagger.Component

@Component(
        dependencies = [MainToolsProvider::class],
        modules = [MainFragmentExportModule::class]
)
interface MainFragmentExportComponent : MainFragmentProvider {

    class Initializer private constructor() {
        companion object {
            fun init(mainToolsProvider: MainToolsProvider): MainFragmentProvider {
                return DaggerMainFragmentExportComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .build()
            }
        }
    }
}