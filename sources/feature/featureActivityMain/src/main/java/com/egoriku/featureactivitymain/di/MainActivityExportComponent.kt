package com.egoriku.featureactivitymain.di

import com.egoriku.core.di.MainActivityProvider
import com.egoriku.featureactivitymain.di.module.MainActivityExportModule
import dagger.Component

@Component(modules = [MainActivityExportModule::class])
interface MainActivityExportComponent : MainActivityProvider {
    class Initializer private constructor() {
        companion object {
            fun init(): MainActivityProvider = DaggerMainActivityExportComponent
                    .builder()
                    .build()
        }
    }
}