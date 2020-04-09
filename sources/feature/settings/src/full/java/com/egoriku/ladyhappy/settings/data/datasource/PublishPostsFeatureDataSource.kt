package com.egoriku.ladyhappy.settings.data.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.settings.data.entity.PublishPostsFeatureEntity
import com.egoriku.network.Result
import com.egoriku.network.firestore.awaitResult

class PublishPostsFeatureDataSource(
        private val firestore: IFirebaseFirestore
) {

    suspend fun load(): Result<PublishPostsFeatureEntity> = firestore.getFirebase()
            .collection("availableFeatures")
            .document("PublishPosts")
            .awaitResult()
}