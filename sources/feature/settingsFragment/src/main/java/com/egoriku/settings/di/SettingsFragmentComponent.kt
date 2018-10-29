package com.egoriku.settings.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import com.egoriku.settings.di.module.SettingsModule
import com.egoriku.settings.presentation.SettingBottomSheetDialogFragment
import dagger.Component

@FragmentScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [SettingsModule::class]
)
internal interface SettingsFragmentComponent {

    fun inject(fragment: SettingBottomSheetDialogFragment)

    companion object {
        fun init(applicationProvider: ApplicationProvider): SettingsFragmentComponent {
            return DaggerSettingsFragmentComponent.builder()
                    .applicationProvider(applicationProvider)
                    .build()
        }
    }
}