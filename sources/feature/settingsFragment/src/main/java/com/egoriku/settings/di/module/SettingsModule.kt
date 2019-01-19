package com.egoriku.settings.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.settings.presentation.SettingsPageContract
import com.egoriku.settings.presentation.SettingsPagePresenter
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @FragmentScope
    @Provides
    fun providePresenter(analytics: IAnalytics): SettingsPageContract.Presenter =
            SettingsPagePresenter(analytics)

}