package com.egoriku.ladyhappy.postcreator.data.entity

import androidx.annotation.Keep
import com.google.firebase.Timestamp

@Keep
data class UploadEntity(
        val images: List<UploadedImageEntity>,
        val title: String,
        val categoryId: Int,
        val subCategoryId: Int,
        val colors: List<Int>,
        val date: Timestamp,
)
