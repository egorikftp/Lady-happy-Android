package com.egoriku.mozaik.model

class PhotoSizes(
        val preview: Size,
        val original: Size
) {

    class Size(val url: String)
}