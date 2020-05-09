package com.egoriku.mozaik.model

import com.egoriku.mozaik.calculator.PostImagePosition

class MozaikImageItem(
        val width: Int = 0,
        val height: Int = 0,
        sizes: PhotoSizes
) {

    internal var position: PostImagePosition? = null
    internal val aspectRatio: Float = width.toFloat() / height.toFloat()

    val previewUrl = sizes.preview
    val originalUrl = sizes.original
}