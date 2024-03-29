package com.egoriku.ladyhappy.core.dateformat

import java.text.SimpleDateFormat
import java.util.*

internal object Formatter {

    val dayMonthYearFormatter by lazy {
        SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    }

    val dayMonthYearTimeFormatter by lazy {
        SimpleDateFormat("dd MMM, yyyy, HH:mm", Locale.getDefault())
    }
}