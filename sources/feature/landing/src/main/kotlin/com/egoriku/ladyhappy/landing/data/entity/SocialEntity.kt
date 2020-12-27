package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

class SocialEntity(
        @PropertyName("url")
        @get:PropertyName("url")
        val url: String? = null,

        @PropertyName("socialType")
        @get:PropertyName("socialType")
        val type: String? = null
)