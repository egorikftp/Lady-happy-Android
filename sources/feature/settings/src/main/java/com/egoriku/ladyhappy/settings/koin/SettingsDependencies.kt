package com.egoriku.ladyhappy.settings.koin

import com.egoriku.ladyhappy.settings.data.datasource.PublishPostsFeatureDataSource
import com.egoriku.ladyhappy.settings.domain.repository.RemoteFeaturesRepository
import com.egoriku.ladyhappy.settings.domain.usecase.AuthenticationUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.SectionsUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.GetAvailableThemesUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.GetThemeUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.SetThemeUseCase
import com.egoriku.ladyhappy.settings.presentation.SettingFragment
import com.egoriku.ladyhappy.settings.presentation.SettingsViewModel
import com.egoriku.ladyhappy.settings.presentation.dialog.theme.ThemeSettingDialogFragment
import com.egoriku.ladyhappy.settings.presentation.dialog.theme.ThemeViewModel
import com.egoriku.ladyhappy.settings.presentation.viewmodel.ReviewViewModel
import com.google.android.play.core.review.ReviewManagerFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    factory { GetThemeUseCase(preferences = get(), dispatchers = get()) }

    scope<SettingFragment> {
        scoped { PublishPostsFeatureDataSource(firestore = get()) }

        scoped { RemoteFeaturesRepository(publishPostsFeatureDataSource = get()) }

        scoped {
            SectionsUseCase(
                    remoteFeaturesRepository = get(),
                    stringResource = get()
            )
        }
        scoped { AuthenticationUseCase(authentication = get()) }

        scoped { ReviewManagerFactory.create(androidContext()) }

        viewModel {
            SettingsViewModel(
                    router = get(),
                    sectionsUseCase = get(),
                    authenticationUseCase = get()
            )
        }

        viewModel {
            ReviewViewModel(reviewManager = get())
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