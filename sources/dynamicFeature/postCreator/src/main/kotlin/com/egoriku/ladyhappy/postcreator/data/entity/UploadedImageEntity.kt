package com.egoriku.ladyhappy.postcreator.data.entity

import androidx.annotation.Keep

@Keep
data class UploadedImageEntity(
        val w: Int,
        val h: Int,
        val url: String
)