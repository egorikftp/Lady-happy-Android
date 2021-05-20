package com.egoriku.ladyhappy.postcreator.domain.model.image

data class UploadImagesParams(
    val images: List<ImageItem>,
    val category: Int,
    val year: Int,
)