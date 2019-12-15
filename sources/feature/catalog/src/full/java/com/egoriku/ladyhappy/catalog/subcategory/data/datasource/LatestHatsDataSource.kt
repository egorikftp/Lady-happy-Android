package com.egoriku.ladyhappy.catalog.subcategory.data.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.HatEntity
import com.egoriku.network.firestore.awaitGet

class LatestHatsDataSource(
        private val firebase: IFirebaseFirestore
) {

    suspend fun fetch(hatsType: Int): List<HatEntity> = firebase.getFirebase()
            .collection("listOfHats")
            .whereEqualTo("hatsType", hatsType)
            .limit(6)
            .awaitGet()
}