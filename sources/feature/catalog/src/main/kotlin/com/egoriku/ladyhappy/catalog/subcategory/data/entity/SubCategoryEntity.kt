package com.egoriku.ladyhappy.catalog.subcategory.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.SerializedName

class SubCategoryEntity(
        @PropertyName("categoryId")
        @get:PropertyName("categoryId")
        val categoryId: Int = -1,

        @PropertyName("subCategoryId")
        @get:PropertyName("subCategoryId")
        val subCategoryId: Int = -1,

        @PropertyName("name")
        @get:PropertyName("name")
        val subCategoryName: String = EMPTY,

        @PropertyName("isPopular")
        @get:PropertyName("isPopular")
        val isPopular: Boolean = false,

        @PropertyName("images")
        @get:PropertyName("images")
        val images: List<Image> = emptyList(),

        @PropertyName("publishedCount")
        @get:PropertyName("publishedCount")
        val publishedCount: Int = 0
)

class Image(
        @PropertyName("h")
        @get:PropertyName("h")
        @SerializedName("h")
        val height: Int = -1,

        @PropertyName("w")
        @get:PropertyName("w")
        @SerializedName("w")
        val width: Int = -1,

        @PropertyName("url")
        @get:PropertyName("url")
        val url: String = EMPTY
)