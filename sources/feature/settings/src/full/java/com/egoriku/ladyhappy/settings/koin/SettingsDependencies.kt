package com.egoriku.ladyhappy.settings.koin

import com.egoriku.ladyhappy.settings.presentation.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel {
        SettingsViewModel(
                authentication = get(),
                router = get()
        )
    }
}