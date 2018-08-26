package com.egoriku.landingfragment.di.module

import com.egoriku.core.actions.MainFragmentAction
import com.egoriku.landingfragment.action.MainFragmentActionImpl
import dagger.Module
import dagger.Provides

@Module
class LandingFragmentExportModule {

    @Provides
    fun provideShowMainActivityAction(): MainFragmentAction = MainFragmentActionImpl()
}