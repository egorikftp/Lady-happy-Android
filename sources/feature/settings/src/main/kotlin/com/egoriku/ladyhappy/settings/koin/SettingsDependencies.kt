package com.egoriku.ladyhappy.settings.koin

import com.egoriku.ladyhappy.settings.data.datasource.IPublishPostsFeatureDataSource
import com.egoriku.ladyhappy.settings.data.datasource.PublishPostsFeatureDataSource
import com.egoriku.ladyhappy.settings.domain.repository.IRemoteFeaturesRepository
import com.egoriku.ladyhappy.settings.domain.repository.RemoteFeaturesRepository
import com.egoriku.ladyhappy.settings.domain.usecase.AuthenticationUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.IAuthenticationUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.ISectionsUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.SectionsUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.GetAvailableThemesUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.GetThemeUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.SetThemeUseCase
import com.egoriku.ladyhappy.settings.presentation.SettingFragment
import com.egoriku.ladyhappy.settings.presentation.SettingsViewModel
import com.egoriku.ladyhappy.settings.presentation.dialog.theme.ThemeSettingDialogFragment
import com.egoriku.ladyhappy.settings.presentation.dialog.theme.ThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    factory { GetThemeUseCase(preferences = get(), dispatchers = get()) }

    scope<SettingFragment> {
        scoped<IPublishPostsFeatureDataSource> { PublishPostsFeatureDataSource(firebase = get()) }

        scoped<IRemoteFeaturesRepository> { RemoteFeaturesRepository(publishPostsFeatureDataSource = get()) }

        scoped<ISectionsUseCase> {
            SectionsUseCase(
                    remoteFeaturesRepository = get(),
                    stringResource = get()
            )
        }
        scoped<IAuthenticationUseCase> { AuthenticationUseCase(authentication = get()) }

        viewModel {
            SettingsViewModel(
                    router = get(),
                    sectionsUseCase = get(),
                    authenticationUseCase = get()
            )
        }
    }

    scope<ThemeSettingDialogFragment> {
        scoped { GetThemeUseCase(preferences = get(), dispatchers = get()) }
        scoped { GetAvailableThemesUseCase(dispatcher = get()) }
        scoped { SetThemeUseCase(preferences = get(), dispatcher = get()) }

        viewModel {
            ThemeViewModel(
                    themeUseCase = get(),
                    availableThemesUseCase = get(),
                    setThemeUseCase = get()
            )
        }
    }
}