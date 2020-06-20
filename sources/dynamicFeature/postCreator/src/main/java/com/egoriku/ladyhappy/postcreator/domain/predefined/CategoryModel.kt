package com.egoriku.ladyhappy.postcreator.domain.predefined

data class CategoryModel(
        val categoryId: Int,
        val name: String
)

data class SubCategory(
        val categoryId: Int,
        val subCategoryId: Int,
        val name: String
)