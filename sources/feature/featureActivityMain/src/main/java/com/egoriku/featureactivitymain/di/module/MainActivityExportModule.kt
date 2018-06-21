package com.egoriku.featureactivitymain.di.module

import com.egoriku.core.actions.ShowMainScreenAction
import com.egoriku.featureactivitymain.actions.ShowMainScreenActionImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityExportModule {

    @Provides
    fun provideShowMainActivityAction(): ShowMainScreenAction = ShowMainScreenActionImpl()
}