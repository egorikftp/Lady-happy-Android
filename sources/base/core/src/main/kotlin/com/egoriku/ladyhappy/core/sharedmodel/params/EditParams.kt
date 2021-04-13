package com.egoriku.ladyhappy.core.sharedmodel.params

import android.os.Parcelable
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditParams(
        val documentReference: String = EMPTY
) : Parcelable
