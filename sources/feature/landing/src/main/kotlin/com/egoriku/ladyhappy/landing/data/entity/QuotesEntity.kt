package com.egoriku.ladyhappy.landing.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class QuotesEntity(
        @PropertyName("quote")
        @JvmField
        val quote: String = EMPTY,

        @PropertyName("author")
        @JvmField
        val author: String = EMPTY
)