package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

data class TeamMemberEntity(
    @PropertyName("imageUrl")
    val personImageUrl: String? = null,

    @PropertyName("name")
    val name: String? = null,

    @PropertyName("skills")
    val skills: String? = null,

    @PropertyName("socialLinks")
    val socialLinks: List<SocialEntity>? = null
)