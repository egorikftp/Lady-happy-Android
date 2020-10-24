package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

data class LandingEntity(
    @PropertyName("aboutInfo")
    val aboutInfo: String? = null,

    @PropertyName("quotes")
    val quotes: List<QuotesEntity>? = null,

    @PropertyName("teamMembers")
    val teamMembers: List<TeamMemberEntity>? = null
)