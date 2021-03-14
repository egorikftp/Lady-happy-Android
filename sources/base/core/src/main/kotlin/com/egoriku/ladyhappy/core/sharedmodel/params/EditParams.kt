package com.egoriku.ladyhappy.core.sharedmodel.params

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditParams(
        val documentReference: String
) : Parcelable
