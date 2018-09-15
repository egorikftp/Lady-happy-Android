package com.egoriku.featureactivitymain.di.module

import com.egoriku.core.actions.IMainActivityAction
import com.egoriku.featureactivitymain.actions.MainActivityAction
import dagger.Module
import dagger.Provides

@Module
class MainActivityExportModule {

    @Provides
    fun provideShowMainActivityAction(): IMainActivityAction = MainActivityAction()
}