package com.egoriku.landingfragment.di

import com.egoriku.core.di.LandingFragmentProvider
import com.egoriku.landingfragment.di.module.LandingFragmentExportModule
import dagger.Component

@Component(modules = [LandingFragmentExportModule::class])
interface LandingFragmentExportComponent : LandingFragmentProvider {

    class Initializer private constructor() {
        companion object {
            fun init(): LandingFragmentProvider = DaggerLandingFragmentExportComponent.create()
        }
    }
}