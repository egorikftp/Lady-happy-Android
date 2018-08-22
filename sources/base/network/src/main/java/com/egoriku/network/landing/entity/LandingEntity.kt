package com.egoriku.network.landing.entity

import android.support.annotation.Keep
import com.egoriku.network.common.Constants
import com.google.firebase.firestore.PropertyName

@Keep
class LandingEntity {

    @PropertyName("aboutInfo")
    val aboutInfo: String? = null

    @PropertyName("quote")
    val quote: String? = null

    @PropertyName("teamMembers")
    val teamMembers: List<TeamMemberEntity>? = null
}

@Keep
data class TeamMemberEntity(
        @PropertyName("imageUrl")
        var personImageUrl: String = Constants.EMPTY,

        @PropertyName("name")
        var name: String = Constants.EMPTY,

        @PropertyName("skills")
        var skills: String = Constants.EMPTY)
/*
        @get:PropertyName("social")
@set:PropertyName("social")
var socialModel: List<SocialModel> = emptyList()*/