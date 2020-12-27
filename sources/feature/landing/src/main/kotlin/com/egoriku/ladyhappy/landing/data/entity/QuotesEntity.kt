package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

class QuotesEntity(
        @PropertyName("quote")
        @get:PropertyName("quote")
        val quote: String? = null,

        @PropertyName("author")
        @get:PropertyName("author")
        val author: String? = null
)