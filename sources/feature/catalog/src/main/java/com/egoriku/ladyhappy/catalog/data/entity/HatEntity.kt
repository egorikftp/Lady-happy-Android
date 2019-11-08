package com.egoriku.ladyhappy.catalog.data.entity

import com.google.firebase.firestore.PropertyName
import java.util.*

class HatEntity {

    @PropertyName("hatsType")
    @JvmField
    val hatsType: Int? = null

    @PropertyName("color")
    @JvmField
    val color: Int? = null

    @PropertyName("imageUrl")
    @JvmField
    val imageUrl: String? = null

    @PropertyName("createdDate")
    @JvmField
    val createdDate: Date? = null

    @PropertyName("shortDescription")
    @JvmField
    val shortDescription: String? = null

    @PropertyName("isPopular")
    @JvmField
    val isPopular: Boolean? = null
}