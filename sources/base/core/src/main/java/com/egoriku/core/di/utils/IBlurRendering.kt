package com.egoriku.core.di.utils

import android.graphics.Bitmap

interface IBlurRendering {

    fun applyBlur(
            radius: Float,
            bitmap: Bitmap
    ): Bitmap
}