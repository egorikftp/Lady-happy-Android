package com.egoriku.ui.ktx

import android.content.Context
import android.util.DisplayMetrics

inline val Context.displayMetrics: DisplayMetrics
    get() = resources.displayMetrics

inline val Context.screenWidth: Int
    get() = displayMetrics.widthPixels

inline val Context.screenHeight: Int
    get() = displayMetrics.heightPixels

inline val Context.screenDensity: Float
    get() = displayMetrics.density

inline val Context.scaledDensity: Float
    get() = displayMetrics.scaledDensity

fun Context.dp2px(dp: Number) = (dp.toFloat() * displayMetrics.density + 0.5f).toInt()

fun Context.sp2px(sp: Number) = (sp.toFloat() * displayMetrics.scaledDensity + 0.5f).toInt()

fun Context.px2dp(px: Number) = (px.toFloat() / displayMetrics.density + 0.5f).toInt()

fun Context.px2sp(px: Number) = (px.toFloat() / displayMetrics.scaledDensity + 0.5f).toInt()

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

