package com.egoriku.network.data.entities

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

    @PropertyName("socialLinks")
    @JvmField
    val socialLinks: List<SocialEntity>? = null
}

class SocialEntity {
    @PropertyName("url")
    @JvmField
    val url: String? = null

    @PropertyName("socialType")
    @JvmField
    val type: String? = null
}
