package com.egoriku.ladyhappy.settings.domain.repository

import com.egoriku.ladyhappy.network.ResultOf.Failure
import com.egoriku.ladyhappy.network.ResultOf.Success
import com.egoriku.ladyhappy.settings.data.datasource.IPublishPostsFeatureDataSource
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.FeatureType
import com.egoriku.ladyhappy.settings.domain.model.FeatureType.PUBLISH_POSTS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RemoteFeaturesRepository(
        private val publishPostsFeatureDataSource: IPublishPostsFeatureDataSource
) : IRemoteFeaturesRepository {

    override suspend fun loadByFeature(feature: FeatureType) = when (feature) {
        PUBLISH_POSTS -> loadPublishPostsFeature()
    }

    private suspend fun loadPublishPostsFeature() = withContext(Dispatchers.IO) {
        when (val result = publishPostsFeatureDataSource.load()) {
            is Success -> Feature.PublishPosts(isAvailable = result.value.isAvailable)
            is Failure -> Feature.PublishPosts(false)
        }
    }
}

interface IRemoteFeaturesRepository {

    suspend fun loadByFeature(feature: FeatureType): Feature
}