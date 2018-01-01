package com.egoriku.ladyhappy.data.entities

import com.egoriku.corelib_kt.Constants
import com.google.firebase.database.PropertyName
import java.util.*

data class NewsEntity(
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

data class NewsDocumentEntity(var news: Map<String, NewsEntity> = hashMapOf())