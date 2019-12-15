package com.egoriku.ladyhappy.catalog.subcategory.data.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.CategoryEntity
import com.egoriku.network.firestore.awaitGet

class SubcategoryDataSource(
        private val firebase: IFirebaseFirestore
) {

    suspend fun fetch(documentId: String): List<CategoryEntity> {
         return firebase.getFirebase()
                .collection("categories/${documentId}/subcategory")
                .awaitGet()
    }
}