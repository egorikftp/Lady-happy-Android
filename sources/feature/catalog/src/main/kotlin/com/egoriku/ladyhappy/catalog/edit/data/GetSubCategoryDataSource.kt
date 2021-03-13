package com.egoriku.ladyhappy.catalog.edit.data

import com.egoriku.ladyhappy.catalog.subcategory.data.entity.SubCategoryEntity
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.constant.CollectionPath.SUBCATEGORIES
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetSubCategoryDataSource(
        private val firebase: IFirebase
) : IGetSubCategoryDataSource {

    override suspend fun load(documentReference: String) = withContext(Dispatchers.IO) {
        firebase.firebaseFirestore
                .collection(SUBCATEGORIES)
                .document(documentReference)
                .awaitResult<SubCategoryEntity>()
    }
}

interface IGetSubCategoryDataSource {

    suspend fun load(documentReference: String): ResultOf<SubCategoryEntity>
}