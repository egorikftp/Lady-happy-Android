package com.egoriku.ladyhappy.postcreator.presentation.model

import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem

data class ImageSection(
        val images: List<ImageItem> = listOf()
)