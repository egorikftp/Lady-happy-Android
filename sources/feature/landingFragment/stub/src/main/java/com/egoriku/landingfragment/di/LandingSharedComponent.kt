package com.egoriku.landingfragment.di

import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.core.di.LandingFragmentProvider
import com.egoriku.landingfragment.action.LandingFragmentAction
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [LandingFragmentExportModule::class])
interface LandingFragmentExportComponent : LandingFragmentProvider {

    class Initializer private constructor() {
        companion object {
            fun init(): LandingFragmentProvider = DaggerLandingFragmentExportComponent.create()
        }
    }
}

@Module
class LandingFragmentExportModule {

    @Provides
    fun provideShowMainActivityAction(): ILandingFragmentAction = LandingFragmentAction()
}