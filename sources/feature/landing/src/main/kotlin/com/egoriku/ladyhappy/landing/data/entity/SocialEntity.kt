package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

class SocialEntity(
        @PropertyName("url")
        @JvmField
        val url: String? = null,

        @PropertyName("socialType")
        @JvmField
        val type: String? = null
)