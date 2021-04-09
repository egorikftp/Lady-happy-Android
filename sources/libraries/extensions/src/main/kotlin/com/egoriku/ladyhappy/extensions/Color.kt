package com.egoriku.ladyhappy.extensions

import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

private const val CONTRAST_THRESHOLD = 1.5f
private const val LIGHTNESS_FACTOR = 0.1f

@ColorInt
fun Int.adjustForBackground(backgroundColor: Int) = when {
    canDisplayOnBackground(backgroundColor) -> this
    else -> adjustLightness(lightnessFactor = LIGHTNESS_FACTOR)
}

fun Int.canDisplayOnBackground(
        @ColorInt background: Int
) = ColorUtils.calculateContrast(this, background) > CONTRAST_THRESHOLD

fun Int.adjustLightness(lightnessFactor: Float): Int {
    val hsl = FloatArray(size = 3).also {
        ColorUtils.colorToHSL(this, it)
    }

    hsl[2] = hsl[2] * lightnessFactor
    return ColorUtils.HSLToColor(hsl)
}
