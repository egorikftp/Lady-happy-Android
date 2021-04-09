package com.egoriku.ladyhappy.mozaik.strategy.internal.model

@Suppress("DataClassShouldBeImmutable")
data class Rect(
        var left: Int = 0,
        var top: Int = 0,
        var right: Int = 0,
        var bottom: Int = 0,
        var offsetHorizontal: Int = 0,
        var offsetVertical: Int = 0
)

fun Rect.updateWith(rect: Rect) {
    left = rect.left
    top = rect.top
    right = rect.right
    bottom = rect.bottom
}

inline val Rect.width
    get() = right - left

inline val Rect.height
    get() = bottom - top
