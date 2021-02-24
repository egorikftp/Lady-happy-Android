package com.egoriku.ladyhappy.core.sharedmodel.params

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostCreatorParams(
        val images: List<Uri> = emptyList()
) : Parcelable
