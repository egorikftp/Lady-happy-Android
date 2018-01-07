package com.egoriku.ladyhappy.data.entities

import com.egoriku.corelib_kt.Constants
import com.google.firebase.firestore.PropertyName
import java.util.*

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

data class NewsEntity(var news: MutableList<SingleNewsEntity> = mutableListOf())