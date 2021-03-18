package com.egoriku.ladyhappy.extensions

import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

@ColorInt
fun Int.adjustForBackground(backgroundColor: Int) = when {
    canDisplayOnBackground(backgroundColor) -> this
    else -> adjustLightness(lightnessFactor = 0.1f)
}

fun Int.canDisplayOnBackground(
        @ColorInt background: Int
) = ColorUtils.calculateContrast(this, background) > 1.5f

fun Int.adjustLightness(lightnessFactor: Float): Int {
    val hsl = FloatArray(size = 3).also {
        ColorUtils.colorToHSL(this, it)
    }

    hsl[2] = hsl[2] * lightnessFactor
    return ColorUtils.HSLToColor(hsl)
}
