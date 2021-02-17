package com.egoriku.ladyhappy.catalog.categories.data.repository

import com.egoriku.ladyhappy.catalog.categories.data.entity.TabEntity
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.constant.CollectionPath.CATEGORIES
import com.egoriku.ladyhappy.core.constant.DocumentField.ID
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class TabRepository(
        private val firebase: IFirebase
) : ITabRepository {

    override suspend fun load() = withContext(Dispatchers.IO) {
        firebase.firebaseFirestore
                .collection(CATEGORIES)
                .orderBy(ID)
                .awaitResult<TabEntity>()
    }
}

interface ITabRepository {

    suspend fun load(): ResultOf<List<TabEntity>>
}