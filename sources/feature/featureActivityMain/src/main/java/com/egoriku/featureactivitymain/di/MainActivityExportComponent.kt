package com.egoriku.featureactivitymain.di

import com.egoriku.core.di.MainScreenActionsProvider
import com.egoriku.core.di.MainToolsProvider
import com.egoriku.featureactivitymain.di.module.MainActivityExportModule
import dagger.Component

@Component(
        dependencies = [MainToolsProvider::class],
        modules = [MainActivityExportModule::class]
)
interface MainActivityExportComponent : MainScreenActionsProvider {
    class Initializer private constructor() {
        companion object {
            fun init(mainToolsProvider: MainToolsProvider): MainScreenActionsProvider {

                return DaggerMainActivityExportComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .build()
            }
        }
    }
}