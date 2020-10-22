package com.egoriku.ladyhappy.settings.data.datasource

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.settings.data.entity.PublishPostsFeatureEntity
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitResult

class PublishPostsFeatureDataSource(private val firestore: IFirebase) {

    suspend fun load(): ResultOf<PublishPostsFeatureEntity> = firestore.firebaseFirestore
            .collection("availableFeatures")
            .document("PublishPosts")
            .awaitResult()
}