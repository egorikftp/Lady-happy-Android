package com.egoriku.ladyhappy.settings.domain.repository

import com.egoriku.ladyhappy.settings.data.datasource.PublishPostsFeatureDataSource
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.FeatureType
import com.egoriku.ladyhappy.settings.domain.model.FeatureType.PUBLISH_POSTS
import com.egoriku.network.ResultOf.Failure
import com.egoriku.network.ResultOf.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteFeaturesRepository(
        private val publishPostsFeatureDataSource: PublishPostsFeatureDataSource
) {

    suspend fun loadFeature(feature: FeatureType) = when (feature) {
        PUBLISH_POSTS -> loadPublishPostsFeature()
    }

    private suspend fun loadPublishPostsFeature() = withContext(Dispatchers.IO) {
        when (val result = publishPostsFeatureDataSource.load()) {
            is Success -> Feature.PublishPosts(isAvailable = result.value.isAvailable)
            is Failure -> Feature.PublishPosts(false)
        }
    }
}