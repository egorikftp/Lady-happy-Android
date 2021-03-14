package com.egoriku.ladyhappy.edit.data.repository

import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.DESCRIPTION
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.IS_POPULAR
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.LAST_EDIT_TIME
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.NAME
import com.egoriku.ladyhappy.edit.data.datasource.UpdateSubCategoryDataSource
import com.egoriku.ladyhappy.edit.domain.repository.IUpdateSubCategoryRepository
import com.egoriku.ladyhappy.network.ResultOf
import com.google.firebase.firestore.FieldValue

internal class UpdateSubCategoryRepository(
        private val updateSubCategoryDataSource: UpdateSubCategoryDataSource
) : IUpdateSubCategoryRepository {

    override suspend fun upload(subCategoryModel: SubCategoryModel): ResultOf<Void> {
        val updatedFields = hashMapOf(
                LAST_EDIT_TIME to FieldValue.serverTimestamp(),
                NAME to subCategoryModel.subCategoryName,
                IS_POPULAR to subCategoryModel.isPopular,
                DESCRIPTION to subCategoryModel.description
        )

        return updateSubCategoryDataSource.update(
                documentReference = subCategoryModel.documentReference,
                data = updatedFields
        )
    }
}