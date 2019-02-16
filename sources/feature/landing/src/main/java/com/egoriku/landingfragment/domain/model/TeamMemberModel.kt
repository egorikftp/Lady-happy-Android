package com.egoriku.landingfragment.domain.model

data class TeamMemberModel(
        val profileImage: String,
        val name: String,
        val skills: String,
        val socialLinks: List<SocialModel>
)