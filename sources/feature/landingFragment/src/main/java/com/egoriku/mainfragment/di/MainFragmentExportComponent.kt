package com.egoriku.mainfragment.di

import com.egoriku.core.di.MainFragmentProvider
import com.egoriku.mainfragment.di.module.LandingFragmentExportModule
import dagger.Component

@Component(modules = [LandingFragmentExportModule::class])
interface MainFragmentExportComponent : MainFragmentProvider {

    class Initializer private constructor() {
        companion object {
            fun init(): MainFragmentProvider = DaggerMainFragmentExportComponent
                    .builder()
                    .build()
        }
    }
}