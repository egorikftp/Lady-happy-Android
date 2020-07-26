package com.egoriku.ladyhappy.settings.koin

import com.egoriku.ladyhappy.settings.data.datasource.PublishPostsFeatureDataSource
import com.egoriku.ladyhappy.settings.domain.repository.RemoteFeaturesRepository
import com.egoriku.ladyhappy.settings.domain.usecase.AuthenticationUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.SectionsUseCase
import com.egoriku.ladyhappy.settings.presentation.SettingFragment
import com.egoriku.ladyhappy.settings.presentation.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    scope<SettingFragment> {
        scoped {
            SectionsUseCase(remoteFeaturesRepository = get())
        }

        scoped {
            AuthenticationUseCase(authentication = get())
        }

        scoped {
            RemoteFeaturesRepository(publishPostsFeatureDataSource = get())
        }

        scoped {
            PublishPostsFeatureDataSource(firestore = get())
        }

        viewModel {
            SettingsViewModel(
                    router = get(),
                    sectionsUseCase = get(),
                    authenticationUseCase = get()
            )
        }
    }
}