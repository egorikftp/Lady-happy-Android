package com.egoriku.usedLibraries.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class License(
        val libraryName: String,
        val libraryLicense: String
) : Parcelable