package com.egoriku.ui.ktx

import android.app.Activity
import android.support.annotation.DimenRes

fun Activity.dimension(@DimenRes id: Int) = resources.getDimension(id)