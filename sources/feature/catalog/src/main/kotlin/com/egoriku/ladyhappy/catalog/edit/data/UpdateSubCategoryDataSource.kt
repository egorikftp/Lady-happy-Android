package com.egoriku.ladyhappy.catalog.edit.data

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.constant.CollectionPath.SUBCATEGORIES
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitResult
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class UpdateSubCategoryDataSource(
        private val firebase: IFirebase
) : IUpdateSubCategoryDataSource {

    override suspend fun update(
            documentReference: String,
            data: HashMap<String, Any>
    ) = withContext(Dispatchers.IO) {
        firebase.firebaseFirestore
                .collection(SUBCATEGORIES)
                .document(documentReference)
                .set(data, SetOptions.merge())
                .awaitResult<Void>()
    }
}

interface IUpdateSubCategoryDataSource {

    suspend fun update(
            documentReference: String,
            data: HashMap<String, Any>
    ): ResultOf<Void>
}