package com.egoriku.network.data.entities.landing

import com.google.firebase.firestore.PropertyName

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