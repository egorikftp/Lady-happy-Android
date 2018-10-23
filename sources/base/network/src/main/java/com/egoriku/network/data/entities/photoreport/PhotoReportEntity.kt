package com.egoriku.network.data.entities.photoreport

import com.google.firebase.firestore.PropertyName
import java.util.*

class PhotoReportEntity {
    @PropertyName("date")
    @JvmField
    val date: Date? = null

    @PropertyName("description")
    @JvmField
    val description: String? = null

    @PropertyName("images")
    @JvmField
    val images: List<String>? = null
}