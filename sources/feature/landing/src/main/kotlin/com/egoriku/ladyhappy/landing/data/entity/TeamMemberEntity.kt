package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

class TeamMemberEntity(
        @PropertyName("imageUrl")
        @get:PropertyName("imageUrl")
        val personImageUrl: String? = null,

        @PropertyName("name")
        @get:PropertyName("name")
        val name: String? = null,

        @PropertyName("skills")
        @get:PropertyName("skills")
        val skills: String? = null,

        @PropertyName("socialLinks")
        @get:PropertyName("socialLinks")
        val socialLinks: List<SocialEntity>? = null
)