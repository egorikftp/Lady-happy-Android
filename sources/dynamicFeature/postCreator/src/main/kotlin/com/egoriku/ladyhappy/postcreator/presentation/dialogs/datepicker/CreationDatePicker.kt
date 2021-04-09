package com.egoriku.ladyhappy.postcreator.presentation.dialogs.datepicker

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.egoriku.ladyhappy.extensions.common.Constants.UTC
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.presentation.KEY_CHOOSER_FRAGMENT_RESULT
import com.egoriku.ladyhappy.postcreator.presentation.KEY_FRAGMENT_RESULT_BUNDLE
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

private const val START_YEAR = 2015
private const val END_YEAR = 2025

class CreationDatePicker {

    private val startDate: Calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC)).apply {
        set(Calendar.YEAR, START_YEAR)
    }

    private val endDate: Calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC)).apply {
        set(Calendar.YEAR, END_YEAR)
    }

    fun getDatePicker(): MaterialDatePicker<Long> {
        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(
                        CalendarConstraints.Builder()
                                .setStart(startDate.timeInMillis)
                                .setEnd(endDate.timeInMillis)
                                .build()
                )
                .build()

        materialDatePicker.addOnPositiveButtonClickListener {
            materialDatePicker.setFragmentResult(
                    KEY_CHOOSER_FRAGMENT_RESULT,
                    bundleOf(KEY_FRAGMENT_RESULT_BUNDLE to DialogResult.CreationDate(it))
            )
        }

        return materialDatePicker
    }
}