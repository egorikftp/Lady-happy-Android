package com.egoriku.ladyhappy.catalog.subcategory.domain.model

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName
import java.util.*

data class HatModel(
        @PropertyName("hatsType")
        @JvmField
        val hatsType: Int = -1,

        @PropertyName("color")
        @JvmField
        val color: Int = -1,

        @PropertyName("images")
        @JvmField
        val images: Image = Image(),

        @PropertyName("createdDate")
        @JvmField
        val createdDate: Date = Date(),

        @PropertyName("shortDescription")
        @JvmField
        val shortDescription: String = EMPTY,

        @PropertyName("isPopular")
        @JvmField
        val isPopular: Boolean = false
)