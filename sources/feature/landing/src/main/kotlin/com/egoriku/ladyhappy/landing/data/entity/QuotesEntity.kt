package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

data class QuotesEntity(
    @PropertyName("quote")
    val quote: String? = null,

    @PropertyName("author")
    val author: String? = null
)