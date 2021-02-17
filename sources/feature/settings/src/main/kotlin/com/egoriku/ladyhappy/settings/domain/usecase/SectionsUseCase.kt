package com.egoriku.ladyhappy.settings.domain.usecase

import com.egoriku.ladyhappy.core.IStringResource
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.FeatureType
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.domain.model.setting.SettingItem
import com.egoriku.ladyhappy.settings.domain.repository.IRemoteFeaturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

internal class SectionsUseCase(
        private val remoteFeaturesRepository: IRemoteFeaturesRepository,
        private val stringResource: IStringResource
) : ISectionsUseCase {

    override suspend fun load(): Flow<Section> = channelFlow {
        launch {
            send(stubFeatures())
            send(settingsSection())
            send(Section.AvailableFeatures(featuresSection()))
        }
    }

    override suspend fun refresh(): Flow<Section> = channelFlow {
        launch {
            send(stubFeatures())
            send(settingsSection())
            send(Section.AvailableFeatures(featuresSection()))
        }
    }

    private fun settingsSection(): Section.Settings = Section.Settings(
            listOf(
                    SettingItem.Header(stringResource = R.string.settings_section_app_settings),
                    SettingItem.Theme(stringResource = R.string.settings_theme_title),

                    SettingItem.Header(stringResource = R.string.settings_section_about),
                    SettingItem.UsedLibraries(stringResource = R.string.settings_used_libraries),
                    SettingItem.Review(stringResource = R.string.settings_review),
                    SettingItem.NonClickable(resource = stringResource.currentVersion)
            )
    )

    private suspend fun featuresSection(): List<Feature> {
        val publishPosts = remoteFeaturesRepository.loadByFeature(FeatureType.PUBLISH_POSTS)

        return listOf(publishPosts).filter {
            it.isAvailable
        }
    }

    private fun stubFeatures() = Section.AvailableFeatures(
            features = listOf(
                    Feature.Stub(),
                    Feature.Stub(),
                    Feature.Stub(),
                    Feature.Stub(),
                    Feature.Stub(),
                    Feature.Stub()
            )
    )
}

interface ISectionsUseCase {

    suspend fun load(): Flow<Section>

    suspend fun refresh(): Flow<Section>
}