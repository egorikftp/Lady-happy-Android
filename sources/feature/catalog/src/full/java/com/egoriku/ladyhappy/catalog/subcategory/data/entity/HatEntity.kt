package com.egoriku.ladyhappy.catalog.subcategory.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName
import java.util.*

class HatEntity {

    @PropertyName("hatsType")
    @JvmField
    val hatsType: Int = -1

    @PropertyName("color")
    @JvmField
    val color: Int = -1

    @PropertyName("images")
    @JvmField
    val images: Map<String, String> = emptyMap()

    @PropertyName("createdDate")
    @JvmField
    val createdDate: Date = Date()

    @PropertyName("shortDescription")
    @JvmField
    val shortDescription: String = EMPTY

    @PropertyName("isPopular")
    @JvmField
    val isPopular: Boolean = false
}