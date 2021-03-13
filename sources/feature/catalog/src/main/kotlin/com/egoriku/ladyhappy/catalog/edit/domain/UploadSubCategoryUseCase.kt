package com.egoriku.ladyhappy.catalog.edit.domain

import com.egoriku.ladyhappy.catalog.edit.data.IUpdateSubCategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.network.ResultOf
import com.google.firebase.firestore.FieldValue

class UploadSubCategoryUseCase(
        private val updateSubCategoryDataSource: IUpdateSubCategoryDataSource
) : IUploadSubCategoryUseCase {

    override suspend fun upload(subCategoryItem: SubCategoryItem): ResultOf<Void> {
        val updatedFields = hashMapOf(
                "lastEditTime" to FieldValue.serverTimestamp(),
                "name" to subCategoryItem.subCategoryName,
                "isPopular" to subCategoryItem.isPopular,
                "description" to subCategoryItem.description
        )

        return updateSubCategoryDataSource.update(
                documentReference = subCategoryItem.documentReference,
                data = updatedFields
        )
    }
}

interface IUploadSubCategoryUseCase {

    suspend fun upload(subCategoryItem: SubCategoryItem): ResultOf<Void>
}