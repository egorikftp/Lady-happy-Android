package com.egoriku.ladyhappy.postcreator.data.entity

import androidx.annotation.Keep

@Keep
data class UploadEntity(
        val images: List<UploadedImageBySize>,
        val title: String,
        val categoryId: Int,
        val subCategoryId: Int,
        val colorId: Int
)
