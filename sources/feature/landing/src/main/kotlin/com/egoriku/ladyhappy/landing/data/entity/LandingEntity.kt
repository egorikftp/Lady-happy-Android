package com.egoriku.ladyhappy.landing.data.entity

import com.google.firebase.firestore.PropertyName

class LandingEntity(
        @PropertyName("aboutInfo")
        @get:PropertyName("aboutInfo")
        val aboutInfo: String? = null,

        @PropertyName("quotes")
        @get:PropertyName("quotes")
        val quotes: List<QuotesEntity>? = null,

        @PropertyName("teamMembers")
        @get:PropertyName("teamMembers")
        val teamMembers: List<TeamMemberEntity>? = null
)