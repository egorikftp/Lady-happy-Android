package com.egoriku.mainfragment.di.module

import com.egoriku.core.actions.MainFragmentAction
import com.egoriku.mainfragment.action.MainFragmentActionImpl
import dagger.Module
import dagger.Provides

@Module
class MainFragmentExportModule {

    @Provides
    fun provideShowMainActivityAction(): MainFragmentAction = MainFragmentActionImpl()
}