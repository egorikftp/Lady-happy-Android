package com.egoriku.ladyhappy.ui.date

import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    val dayMonthYearFormatter by lazy {
        SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    }
}