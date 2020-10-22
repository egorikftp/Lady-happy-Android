package com.egoriku.ladyhappy.postcreator.domain.predefined

data class CategoryModel(
        val categoryId: Int,
        val name: String
)

data class SubCategoryModel(
        val categoryId: Int,
        val subCategoryId: Int,
        val name: String
)

data class ColorModel(
        val colorId: Int,
        val name: String,
        val color: String
)