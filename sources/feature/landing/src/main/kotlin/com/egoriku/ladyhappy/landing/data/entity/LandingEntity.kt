package com.egoriku.ladyhappy.landing.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class LandingEntity(
    @PropertyName("aboutInfo")
    @JvmField
    val aboutInfo: String = EMPTY,

    @PropertyName("quotes")
    @JvmField
    val quotes: List<QuotesEntity> = emptyList(),

    @PropertyName("teamMembers")
    @JvmField
    val teamMembers: List<TeamMemberEntity> = emptyList()
)