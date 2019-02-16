package com.egoriku.landingfragment.data.entity

import com.google.firebase.firestore.PropertyName

class LandingEntity {
    @PropertyName("aboutInfo")
    @JvmField
    val aboutInfo: String? = null

    @PropertyName("quotes")
    @JvmField
    val quotes: List<QuotesEntity>? = null

    @PropertyName("teamMembers")
    @JvmField
    val teamMembers: List<TeamMemberEntity>? = null
}