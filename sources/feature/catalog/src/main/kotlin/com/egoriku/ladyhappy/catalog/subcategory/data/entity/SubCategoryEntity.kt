package com.egoriku.ladyhappy.catalog.subcategory.data.entity

import com.egoriku.ladyhappy.core.sharedmodel.entity.ImageEntity
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class SubCategoryEntity(
        @PropertyName("categoryId")
        @JvmField
        val categoryId: Int = -1,

        @PropertyName("subCategoryId")
        @JvmField
        val subCategoryId: Int = -1,

        @PropertyName("name")
        @JvmField
        val subCategoryName: String = EMPTY,

        @PropertyName("isPopular")
        @JvmField
        val isPopular: Boolean = false,

        @PropertyName("images")
        @JvmField
        val images: List<ImageEntity> = emptyList(),

        @PropertyName("publishedCount")
        @JvmField
        val publishedCount: Int = 0,

        @PropertyName("description")
        @JvmField
        val description: String = EMPTY,
)