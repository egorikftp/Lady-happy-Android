@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.extensions

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.widget.ImageViewCompat

inline fun ImageView.setImageTintList(@ColorRes resId: Int) {
    ImageViewCompat.setImageTintList(this, colorStateListCompat(resId))
}