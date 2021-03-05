package com.egoriku.ladyhappy.core.sharedmodel.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class ImageEntity(
        @PropertyName("h")
        @JvmField
        val height: Int = -1,

        @PropertyName("w")
        @JvmField
        val width: Int = -1,

        @PropertyName("url")
        @JvmField
        val url: String = EMPTY
)