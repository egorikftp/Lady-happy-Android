package com.egoriku.ladyhappy.settings.data.datasource

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.constant.CollectionPath.AVAILABLE_FEATURES
import com.egoriku.ladyhappy.core.constant.CollectionPath.PUBLISH_POSTS
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitResult
import com.egoriku.ladyhappy.settings.data.entity.PublishPostsFeatureEntity

internal class PublishPostsFeatureDataSource(
        private val firebase: IFirebase
) : IPublishPostsFeatureDataSource {

    override suspend fun load() = firebase.firebaseFirestore
            .collection(AVAILABLE_FEATURES)
            .document(PUBLISH_POSTS)
            .awaitResult<PublishPostsFeatureEntity>()
}

interface IPublishPostsFeatureDataSource {

    suspend fun load(): ResultOf<PublishPostsFeatureEntity>
}