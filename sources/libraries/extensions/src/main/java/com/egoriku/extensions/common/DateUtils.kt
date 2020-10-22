package com.egoriku.extensions.common

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    val formatter by lazy {
        SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    }
}

fun Date?.toNewsDate(): String = when (this) {
    null -> ""
    else -> DateUtils.formatter.format(this)
}