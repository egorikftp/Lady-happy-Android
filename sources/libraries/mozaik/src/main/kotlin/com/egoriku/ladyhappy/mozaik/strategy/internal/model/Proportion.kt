package com.egoriku.ladyhappy.mozaik.strategy.internal.model

import android.graphics.Point
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.mozaik.strategy.internal.extension.half
import kotlin.math.roundToInt

@Suppress("NOTHING_TO_INLINE")
internal class Proportion(
    private val width: Int,
    height: Int,
    private val divider: Int
) {

    constructor(mozaikItem: MozaikItem, divider: Int) : this(
        width = mozaikItem.width,
        height = mozaikItem.height,
        divider = divider
    )

    private val imageProportion = width.toFloat() / height.toFloat()

    private inline fun resize(width: Int): Point {
        val h = (width.toFloat() / imageProportion).roundToInt()

        return Point(width, h)
    }

    fun getRect(
        width: Int,
        leftDivider: Boolean = false,
        rightDivider: Boolean = false,
        topDivider: Boolean = false,
        offsetHorizontal: Int = 0,
        offsetVertical: Int = 0
    ): Rect {
        val rightDiv = if (rightDivider) divider.half() else 0
        val leftDiv = if (leftDivider) divider.half() else 0
        val topDiv = if (topDivider) divider else 0

        val point = resize(width = width - leftDiv - rightDiv)

        return Rect(
            left = offsetHorizontal + leftDiv,
            right = point.x + offsetHorizontal + leftDiv,
            top = offsetVertical + topDiv,
            bottom = point.y + offsetVertical + topDiv,
            offsetHorizontal = point.x + offsetHorizontal + leftDiv + rightDiv,
            offsetVertical = point.y + offsetVertical + topDiv
        )
    }
}