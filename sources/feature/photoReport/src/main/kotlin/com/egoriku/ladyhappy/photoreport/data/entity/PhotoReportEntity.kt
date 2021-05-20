package com.egoriku.ladyhappy.photoreport.data.entity

import com.egoriku.ladyhappy.core.sharedmodel.entity.ImageEntity
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName
import java.util.*

class PhotoReportEntity(
    @PropertyName("date")
    @JvmField
    val date: Date = Date(),

    @PropertyName("description")
    @JvmField
    val description: String = EMPTY,

    @PropertyName("images")
    @JvmField
    val images: List<ImageEntity> = emptyList()
)