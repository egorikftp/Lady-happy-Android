package com.egoriku.network.data.entities.landing

import com.google.firebase.firestore.PropertyName

class SocialEntity {
    @PropertyName("url")
    @JvmField
    val url: String? = null

    @PropertyName("socialType")
    @JvmField
    val type: String? = null
}