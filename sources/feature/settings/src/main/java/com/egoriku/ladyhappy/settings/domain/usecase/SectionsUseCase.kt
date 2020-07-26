package com.egoriku.ladyhappy.settings.domain.usecase

import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.FeatureType
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.domain.model.SettingItem
import com.egoriku.ladyhappy.settings.domain.repository.RemoteFeaturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class SectionsUseCase(private val remoteFeaturesRepository: RemoteFeaturesRepository) {

    suspend fun load(): Flow<Section> = channelFlow {
        launch {
            send(stubFeatures())
            send(Section.AvailableFeatures(featuresSection()))
            send(settingsSection())
        }
    }

    suspend fun refresh(): Flow<Section> = channelFlow {
        launch {
            send(stubFeatures())
            send(Section.AvailableFeatures(featuresSection()))
            send(settingsSection())
        }
    }

    private fun settingsSection(): Section.Settings {
        return Section.Settings(
                listOf(
                        SettingItem(textResId = R.string.settings_theme_title)
                )
        )
    }

    private suspend fun featuresSection(): List<Feature> {
        val publishPosts = remoteFeaturesRepository.loadFeature(FeatureType.PUBLISH_POSTS)

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