package com.egoriku.featureactivitymain.di.module

import com.egoriku.core.actions.MainActivityAction
import com.egoriku.featureactivitymain.actions.MainActivityActionImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityExportModule {

    @Provides
    fun provideShowMainActivityAction(): MainActivityAction = MainActivityActionImpl()
}