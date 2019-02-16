package com.egoriku.landing.domain.model

data class LandingModel(
        val aboutInfo: String,
        val quotes: List<QuotesModel>,
        val teamMembers: List<TeamMemberModel>
)