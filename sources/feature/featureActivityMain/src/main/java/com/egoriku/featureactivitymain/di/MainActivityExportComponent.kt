package com.egoriku.featureactivitymain.di

import com.egoriku.core.di.MainActivityProvider
import com.egoriku.core.di.MainToolsProvider
import com.egoriku.featureactivitymain.di.module.MainActivityExportModule
import dagger.Component

@Component(
        dependencies = [MainToolsProvider::class],
        modules = [MainActivityExportModule::class]
)
interface MainActivityExportComponent : MainActivityProvider {
    class Initializer private constructor() {
        companion object {
            fun init(mainToolsProvider: MainToolsProvider): MainActivityProvider {

                return DaggerMainActivityExportComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .build()
            }
        }
    }
}