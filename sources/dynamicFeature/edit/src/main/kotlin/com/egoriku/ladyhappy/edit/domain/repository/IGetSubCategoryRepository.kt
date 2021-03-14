package com.egoriku.ladyhappy.edit.domain.repository

import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel

interface IGetSubCategoryRepository {

    suspend fun load(documentReference: String): SubCategoryModel?
}