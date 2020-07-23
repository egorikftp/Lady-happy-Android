package com.egoriku.ladyhappy.catalog.subcategory.data.repository

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.SubcategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.SubCategoryEntity
import com.egoriku.network.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubcategoryRepository(private val subcategoryDataSource: SubcategoryDataSource) {

    suspend fun fetchSubCategories(categoryId: Int): ResultOf<List<SubCategoryEntity>> = withContext(Dispatchers.IO) {
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