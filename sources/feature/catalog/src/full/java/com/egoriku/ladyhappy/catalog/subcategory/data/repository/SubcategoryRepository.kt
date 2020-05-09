package com.egoriku.ladyhappy.catalog.subcategory.data.repository

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.SubcategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.SubCategoryEntity
import com.egoriku.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubcategoryRepository(private val subcategoryDataSource: SubcategoryDataSource) {

    suspend fun fetchSubCategories(categoryId: Int): Result<List<SubCategoryEntity>> = withContext(Dispatchers.IO) {
        runCatching {
            val value = subcategoryDataSource.fetch(categoryId)

            if (value.isEmpty()) {
                Result.Error(Exception("Empty response"))
            } else {
                Result.Success(value)
            }
        }.getOrElse {
            Result.Error(it)
        }
    }
}