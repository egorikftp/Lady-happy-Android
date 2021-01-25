package com.egoriku.ladyhappy.postcreator.data.entity

import androidx.annotation.Keep

@Keep
data class UploadedImageBySize(
        val preview: UploadedImageEntity,
        val fullSize: UploadedImageEntity
)