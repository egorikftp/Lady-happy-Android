package com.egoriku.ladyhappy.catalog.subcategory.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

data class SubCategoryEntity(
    @PropertyName("categoryId")
    val categoryId: Int = -1,

    @PropertyName("subCategoryId")
    val subCategoryId: Int = -1,

    @PropertyName("name")
    val subCategoryName: String = EMPTY,

    @PropertyName("isPopular")
    val isPopular: Boolean = false,

    @PropertyName("images")
    val images: List<Image> = emptyList(),

    @PropertyName("publishedCount")
    val publishedCount: Int = 0
)

data class Image(
    @PropertyName("h")
    val height: Int = -1,

    @PropertyName("w")
    val width: Int = -1,

    @PropertyName("url")
    val url: String = EMPTY
)