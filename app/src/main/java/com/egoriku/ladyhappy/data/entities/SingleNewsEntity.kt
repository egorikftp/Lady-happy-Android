package com.egoriku.ladyhappy.data.entities

import android.support.annotation.Keep
import com.egoriku.corelib_kt.Constants
import com.google.firebase.firestore.PropertyName
import java.util.*

@Keep
data class SingleNewsEntity(
        @get:PropertyName("date")
        @set:PropertyName("date")
        var date: Date = Date(),

        @get:PropertyName("description")
        @set:PropertyName("description")
        var description: String = Constants.EMPTY,

        @get:PropertyName("images")
        @set:PropertyName("images")
        var images: List<String> = mutableListOf()
)