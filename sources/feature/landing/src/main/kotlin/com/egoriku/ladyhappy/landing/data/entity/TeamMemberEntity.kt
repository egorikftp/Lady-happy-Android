package com.egoriku.ladyhappy.landing.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class TeamMemberEntity(
    @PropertyName("imageUrl")
    @JvmField
    val personImageUrl: String = EMPTY,

    @PropertyName("name")
    @JvmField
    val name: String = EMPTY,

    @PropertyName("skills")
    @JvmField
    val skills: String = EMPTY,

    @PropertyName("socialLinks")
    @JvmField
    val socialLinks: List<SocialEntity> = emptyList()
)