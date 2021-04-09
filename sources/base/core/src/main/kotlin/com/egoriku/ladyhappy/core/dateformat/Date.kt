package com.egoriku.ladyhappy.core.dateformat

import com.egoriku.ladyhappy.extensions.common.Constants.UTC
import java.util.*

fun Date.ddMMMyyyy(): String = Formatter.dayMonthYearFormatter.format(this)

fun Date.ddMMMyyyyHHmm(): String = Formatter.dayMonthYearTimeFormatter.format(this)

fun Date.year(): Int {
    val cal = Calendar.getInstance(TimeZone.getTimeZone(UTC)).apply {
        time = this@year
    }

    return cal[Calendar.YEAR]
}