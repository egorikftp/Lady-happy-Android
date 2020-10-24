package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

data class SocialEntity(
    @PropertyName("url")
    val url: String? = null,

    @PropertyName("socialType")
    val type: String? = null
)