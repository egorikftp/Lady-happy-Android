package com.egoriku.ladyhappy.core.sharedmodel.domain

import com.egoriku.ladyhappy.mozaik.model.MozaikItem

data class SubCategoryModel(
    val categoryId: Int,
    val subCategoryId: Int,
    val subCategoryName: String,
    val isPopular: Boolean,
    val publishedCount: Int,
    val images: List<MozaikItem>,
    val description: String,
    val documentReference: String,
    val lastEditTime: String
)