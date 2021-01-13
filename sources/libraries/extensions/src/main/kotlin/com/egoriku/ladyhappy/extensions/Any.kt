@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import android.view.View
import android.widget.ImageView

inline fun <reified T : Any> Any?.castOrNull(): T? = this as? T

inline fun View.toImageView() = this as ImageView