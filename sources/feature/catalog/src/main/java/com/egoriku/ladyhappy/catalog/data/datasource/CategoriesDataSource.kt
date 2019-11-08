package com.egoriku.ladyhappy.catalog.data.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.catalog.data.entity.CategoryEntity

class CategoriesDataSource(
        private val firebase: IFirebaseFirestore
) {

    fun fetch(): List<CategoryEntity> = emptyList()
}