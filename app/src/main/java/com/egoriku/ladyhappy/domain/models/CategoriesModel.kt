package com.egoriku.ladyhappy.domain.models

import com.egoriku.corelib_kt.Constants


data class SingleCategoryModel(
        val id: Int = 0,
        val key: String = Constants.EMPTY,
        val title: String = Constants.EMPTY,
        val imageUrl: String = Constants.EMPTY
)

data class CategoriesModel(val categories: List<SingleCategoryModel> = listOf())