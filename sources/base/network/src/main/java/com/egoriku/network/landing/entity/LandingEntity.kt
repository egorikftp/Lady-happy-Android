package com.egoriku.network.landing.entity

import android.support.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
class LandingEntity {

    @PropertyName("aboutInfo")
    val aboutInfo: String? = null

    @PropertyName("quote")
    val quote: String? = null
}