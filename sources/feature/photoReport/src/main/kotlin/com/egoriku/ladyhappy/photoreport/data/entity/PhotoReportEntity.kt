package com.egoriku.ladyhappy.photoreport.data.entity

import com.google.firebase.firestore.PropertyName
import java.util.*

class PhotoReportEntity(
        @PropertyName("date")
        @get:PropertyName("date")
        val date: Date? = null,

        @PropertyName("description")
        @get:PropertyName("description")
        val description: String? = null,

        @PropertyName("images")
        @get:PropertyName("images")
        val images: List<String>? = null
)