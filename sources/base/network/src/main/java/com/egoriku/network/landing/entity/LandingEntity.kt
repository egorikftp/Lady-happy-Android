package com.egoriku.network.landing.entity

import android.support.annotation.Keep
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

@Keep
class TeamMemberEntity {

    @PropertyName("imageUrl")
    @JvmField
    val personImageUrl: String? = null

    @PropertyName("name")
    @JvmField
    val name: String? = null

    @PropertyName("skills")
    @JvmField
    val skills: String? = null
}
/*
        @get:PropertyName("social")
@set:PropertyName("social")
var socialModel: List<SocialModel> = emptyList()*/