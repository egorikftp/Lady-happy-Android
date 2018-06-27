package com.egoriku.featureactivitymain.di.module

import com.egoriku.core.actions.ShowMainActivityAction
import com.egoriku.featureactivitymain.actions.ShowMainActibityActionImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityExportModule {

    @Provides
    fun provideShowMainActivityAction(): ShowMainActivityAction = ShowMainActibityActionImpl()
}