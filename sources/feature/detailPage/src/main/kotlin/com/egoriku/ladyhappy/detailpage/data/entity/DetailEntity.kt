package com.egoriku.ladyhappy.detailpage.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName
import java.util.*

class DetailEntity(
        @PropertyName("name")
        @JvmField
        val name: String = EMPTY,

        @PropertyName("images")
        @JvmField
        val images: List<ImageEntity> = emptyList(),

        @PropertyName("title")
        @JvmField
        val title: String = EMPTY,

        @PropertyName("date")
        @JvmField
        val date: Date = Date()
)