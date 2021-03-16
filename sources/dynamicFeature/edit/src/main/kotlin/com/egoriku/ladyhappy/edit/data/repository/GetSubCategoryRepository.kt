package com.egoriku.ladyhappy.edit.data.repository

import com.egoriku.ladyhappy.core.IStringResource
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.edit.data.datasource.GetSubCategoryDataSource
import com.egoriku.ladyhappy.edit.data.mapper.SubCategoryEntityMapper
import com.egoriku.ladyhappy.edit.domain.repository.IGetSubCategoryRepository
import com.egoriku.ladyhappy.network.ResultOf

internal class GetSubCategoryRepository(
        private val getSubCategoryDataSource: GetSubCategoryDataSource,
        private val stringResource: IStringResource
) : IGetSubCategoryRepository {

    override suspend fun load(documentReference: String): SubCategoryModel? {
        return when (val resultOf = getSubCategoryDataSource.load(documentReference)) {
            is ResultOf.Success -> SubCategoryEntityMapper(stringResource).invoke(resultOf.value)
            is ResultOf.Failure -> null
        }
    }
}