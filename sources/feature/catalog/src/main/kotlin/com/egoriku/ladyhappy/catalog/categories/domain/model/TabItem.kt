package com.egoriku.ladyhappy.catalog.categories.domain.model

data class TabItem(
    val categoryId: Int,
    val categoryName: String,
    val editCount: Int = 0
)