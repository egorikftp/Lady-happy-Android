package com.egoriku.mozaik.model

import com.egoriku.mozaik.calculator.PostImagePosition

class MozaikImageItem(val photo: Photo) {

    var position: PostImagePosition? = null

    val width: Int = photo.width
    val height: Int = photo.height
    val aspectRatio: Float = width.toFloat() / height.toFloat()

    fun getPreviewUrl(): String = photo.sizes.preview.url
}