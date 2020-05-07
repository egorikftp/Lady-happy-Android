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

    @PropertyName("images")
    @JvmField
    val images: Map<String, String> = emptyMap()
}