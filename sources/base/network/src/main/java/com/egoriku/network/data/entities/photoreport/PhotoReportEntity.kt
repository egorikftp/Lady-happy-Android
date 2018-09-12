package com.egoriku.network.data.entities.photoreport

import android.support.annotation.Keep
import com.google.firebase.firestore.PropertyName
import java.util.*

@Keep
class PhotoReportEntity {
    @PropertyName("date")
    @JvmField
    val date: Date? = null

    @PropertyName("description")
    @JvmField
    val description: String? = null

    @PropertyName("images")
    @JvmField
    val images: List<String>? = null
}