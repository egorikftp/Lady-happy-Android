package com.egoriku.ladyhappy.usedLibraries.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class License(
    val libraryName: String,
    val libraryLicense: String
) : Parcelable