package com.egoriku.ladyhappy.photoreport.data.entity

import com.google.firebase.firestore.PropertyName
import java.util.*

data class PhotoReportEntity(
    @PropertyName("date")
    val date: Date? = null,

    @PropertyName("description")
    val description: String? = null,

    @PropertyName("images")
    val images: List<String>? = null
)