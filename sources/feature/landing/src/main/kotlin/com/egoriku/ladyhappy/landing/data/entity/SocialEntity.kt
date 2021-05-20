package com.egoriku.ladyhappy.landing.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class SocialEntity(
    @PropertyName("url")
    @JvmField
    val url: String = EMPTY,

    @PropertyName("socialType")
    @JvmField
    val type: String = EMPTY
)