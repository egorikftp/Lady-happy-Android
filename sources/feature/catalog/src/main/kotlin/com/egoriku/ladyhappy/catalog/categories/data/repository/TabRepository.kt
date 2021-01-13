package com.egoriku.ladyhappy.catalog.categories.data.repository

import com.egoriku.ladyhappy.catalog.categories.data.entity.TabEntity
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TabRepository(private val firebase: IFirebase) {

    suspend fun load(): ResultOf<List<TabEntity>> = withContext(Dispatchers.IO) {
        firebase.firebaseFirestore
                .collection("categories")
                .orderBy("id")
                .awaitResult()
    }
}