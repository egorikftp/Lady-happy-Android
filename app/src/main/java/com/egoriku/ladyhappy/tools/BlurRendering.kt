package com.egoriku.ladyhappy.tools

import android.content.Context
import android.graphics.Bitmap
import com.egoriku.core.di.utils.IBlurRendering
import com.egoriku.ladyhappy.rendering.RenderScriptGaussianBlur

class BlurRendering(context: Context) : IBlurRendering {

    private val blurRendering = RenderScriptGaussianBlur(context)

    override fun applyBlur(
            radius: Float,
            bitmap: Bitmap
    ): Bitmap = blurRendering.gaussianBlur(radius, bitmap)
}