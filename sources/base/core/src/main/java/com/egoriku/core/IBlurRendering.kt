package com.egoriku.core

import android.graphics.Bitmap

interface IBlurRendering {

    fun applyBlur(
            radius: Float,
            bitmap: Bitmap
    ): Bitmap
}