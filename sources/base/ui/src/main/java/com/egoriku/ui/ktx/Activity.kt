@file:Suppress("unused")

package com.egoriku.ui.ktx

import android.app.Activity
import androidx.annotation.DimenRes

fun Activity.dimension(@DimenRes id: Int) = resources.getDimension(id)