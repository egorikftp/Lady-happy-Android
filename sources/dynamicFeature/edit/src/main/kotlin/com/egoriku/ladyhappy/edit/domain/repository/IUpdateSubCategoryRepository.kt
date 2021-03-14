package com.egoriku.ladyhappy.edit.domain.repository

import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.network.ResultOf

interface IUpdateSubCategoryRepository {

    suspend fun upload(subCategoryModel: SubCategoryModel): ResultOf<Void>
}