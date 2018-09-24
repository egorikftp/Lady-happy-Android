package com.egoriku.landingfragment.di.module

import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.landingfragment.action.LandingFragmentAction
import dagger.Module
import dagger.Provides

@Module
class LandingFragmentExportModule {

    @Provides
    fun provideShowMainActivityAction(): ILandingFragmentAction = LandingFragmentAction()
}