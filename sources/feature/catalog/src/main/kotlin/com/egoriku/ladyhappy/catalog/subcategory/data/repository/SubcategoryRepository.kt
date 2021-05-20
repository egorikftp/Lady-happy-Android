package com.egoriku.ladyhappy.catalog.subcategory.data.repository

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.ISubcategoryDataSource
import com.egoriku.ladyhappy.core.sharedmodel.entity.SubCategoryEntity
import com.egoriku.ladyhappy.network.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SubcategoryRepository(
    private val subcategoryDataSource: ISubcategoryDataSource
) : ISubcategoryRepository {

    override suspend fun fetchSubCategories(categoryId: Int) = withContext(Dispatchers.IO) {
        runCatching {
            val value = subcategoryDataSource.fetch(categoryId)

            if (value.isEmpty()) {
                ResultOf.Failure(Exception("Empty response"))
            } else {
                ResultOf.Success(value)
            }
        }.getOrElse {
            ResultOf.Failure(it)
        }
    }
}

interface ISubcategoryRepository {

    suspend fun fetchSubCategories(categoryId: Int): ResultOf<List<SubCategoryEntity>>
}