package com.egoriku.settings.di

import com.egoriku.core.actions.ISettingsFragmentAction
import com.egoriku.core.di.SettingsFeatureProvider
import com.egoriku.settings.action.SettingsFragmentAction
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [SettingsSharedModule::class])
interface SettingsFeatureSharedComponent : SettingsFeatureProvider {

    companion object {
        fun init(): SettingsFeatureProvider = DaggerSettingsFeatureSharedComponent.create()
    }
}

@Module
class SettingsSharedModule {

    @Provides
    fun provideAction(): ISettingsFragmentAction = SettingsFragmentAction()
}
