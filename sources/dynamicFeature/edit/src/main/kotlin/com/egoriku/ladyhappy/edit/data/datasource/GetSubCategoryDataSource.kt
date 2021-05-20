package com.egoriku.ladyhappy.edit.data.datasource

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.sharedmodel.entity.SubCategoryEntity
import com.egoriku.ladyhappy.core.sharedmodel.key.CollectionPath.SUBCATEGORIES
import com.egoriku.ladyhappy.network.firestore.awaitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetSubCategoryDataSource(private val firebase: IFirebase) {

    suspend fun load(documentReference: String) = withContext(Dispatchers.IO) {
        firebase.firebaseFirestore
            .collection(SUBCATEGORIES)
            .document(documentReference)
            .awaitResult<SubCategoryEntity>()
    }
}