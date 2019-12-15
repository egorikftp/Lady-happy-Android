package com.egoriku.ladyhappy.catalog.subcategory.data.repository

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.SubcategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.CategoryEntity
import com.egoriku.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubcategoryRepository(
        private val subcategoryDataSource: SubcategoryDataSource
) {

    suspend fun fetchSubCategories(documentId: String): Result<List<CategoryEntity>> = withContext(Dispatchers.IO) {
        runCatching {
            Result.Success(subcategoryDataSource.fetch(documentId))
        }.getOrElse {
            Result.Error(it)
        }
    }
}