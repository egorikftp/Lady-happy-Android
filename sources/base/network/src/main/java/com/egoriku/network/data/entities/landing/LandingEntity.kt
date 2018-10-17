package com.egoriku.network.data.entities.landing

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
class LandingEntity {
    @PropertyName("aboutInfo")
    @JvmField
    val aboutInfo: String? = null

    @PropertyName("quote")
    @JvmField
    val quote: String? = null

    @PropertyName("teamMembers")
    @JvmField
    val teamMembers: List<TeamMemberEntity>? = null
}