package com.egoriku.ladyhappy.postcreator.domain.predefined

data class CategoryModel(
        val categoryId: Int = -1,
        val name: String,
        val subCategories: List<SubCategory>
)

data class SubCategory(
        val name: String,
        val type: Int
)