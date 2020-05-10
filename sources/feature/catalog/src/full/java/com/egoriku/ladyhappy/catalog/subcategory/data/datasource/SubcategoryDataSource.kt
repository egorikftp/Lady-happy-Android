package com.egoriku.ladyhappy.catalog.subcategory.data.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.SubCategoryEntity
import com.egoriku.network.firestore.awaitGet

class SubcategoryDataSource(private val firebase: IFirebaseFirestore) {

    suspend fun fetch(categoryId: Int): List<SubCategoryEntity> {
        return firebase.getFirebase()
                .collection("subcategories")
                .whereEqualTo("categoryId", categoryId)
                .awaitGet()
    }
}