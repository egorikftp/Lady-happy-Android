package com.egoriku.ladyhappy.catalog.category.data.repository

import com.egoriku.ladyhappy.catalog.category.data.datasource.CategoriesDataSource
import com.egoriku.ladyhappy.catalog.category.data.entity.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesRepository(
        private val categoriesDataSource: CategoriesDataSource
) {

    suspend fun fetchCategories(): List<CategoryEntity> = withContext(Dispatchers.IO) {
        runCatching {
            categoriesDataSource.fetch()
        }.getOrDefault(emptyList())
    }
}