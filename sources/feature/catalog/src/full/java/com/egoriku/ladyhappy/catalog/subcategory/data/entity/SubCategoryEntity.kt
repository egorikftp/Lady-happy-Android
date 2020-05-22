package com.egoriku.ladyhappy.catalog.subcategory.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class SubCategoryEntity {
    @PropertyName("categoryId")
    @JvmField
    val categoryId: Int = -1

    @PropertyName("subCategoryId")
    @JvmField
    val subCategoryId: Int = -1

    @PropertyName("name")
    @JvmField
    val categoryName: String = EMPTY

    @PropertyName("isPopular")
    @JvmField
    val isPopular: Boolean = false

    @PropertyName("images")
    @JvmField
    val images: List<Image> = emptyList()

    @PropertyName("publishedCount")
    @JvmField
    val publishedCount: Int = 0
}

class Image {
    @PropertyName("h")
    @JvmField
    val height: Int = -1

    @PropertyName("w")
    @JvmField
    val width: Int = -1

    @PropertyName("url")
    @JvmField
    val url: String = EMPTY
}