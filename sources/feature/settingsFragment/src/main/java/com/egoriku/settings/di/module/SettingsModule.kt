package com.egoriku.settings.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.settings.presentation.SettingsPageContract
import com.egoriku.settings.presentation.SettingsPagePresenter
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @FragmentScope
    @Provides
    fun providePresenter(analyticsHelper: IAnalyticsHelper): SettingsPageContract.Presenter =
            SettingsPagePresenter(analyticsHelper)

}