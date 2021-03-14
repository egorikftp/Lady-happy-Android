package com.egoriku.ladyhappy.catalog.subcategory.data.datasource

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.sharedmodel.entity.SubCategoryEntity
import com.egoriku.ladyhappy.core.sharedmodel.key.CollectionPath.SUBCATEGORIES
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.CATEGORY_ID
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.SUB_CATEGORY_ID
import com.egoriku.ladyhappy.network.firestore.awaitGet

internal class SubcategoryDataSource(
        private val firebase: IFirebase
) : ISubcategoryDataSource {

    override suspend fun fetch(categoryId: Int): List<SubCategoryEntity> {
        return firebase.firebaseFirestore
                .collection(SUBCATEGORIES)
                .whereEqualTo(CATEGORY_ID, categoryId)
                .orderBy(SUB_CATEGORY_ID)
                .awaitGet()
    }
}

interface ISubcategoryDataSource {

    suspend fun fetch(categoryId: Int): List<SubCategoryEntity>
}