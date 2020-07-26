package com.egoriku.landing.data.entity

import com.google.firebase.firestore.PropertyName

class QuotesEntity {
    @PropertyName("quote")
    @JvmField
    val quote: String? = null

    @PropertyName("author")
    @JvmField
    val author: String? = null
}