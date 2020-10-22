package com.egoriku.ladyhappy.tools

import android.content.Context
import android.graphics.Bitmap
import com.egoriku.core.IBlurRendering
import com.egoriku.ladyhappy.rendering.RenderScriptGaussianBlur

internal class BlurRendering(context: Context) : IBlurRendering {

    private val gaussianBlur = RenderScriptGaussianBlur(context)

    override fun applyBlur(
            radius: Float,
            bitmap: Bitmap
    ): Bitmap = gaussianBlur.gaussianBlur(radius, bitmap)
}