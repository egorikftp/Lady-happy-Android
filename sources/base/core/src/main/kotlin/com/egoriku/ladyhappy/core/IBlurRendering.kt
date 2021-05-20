package com.egoriku.ladyhappy.core

import android.graphics.Bitmap

interface IBlurRendering {

    fun applyBlur(
        radius: Float,
        bitmap: Bitmap
    ): Bitmap
}