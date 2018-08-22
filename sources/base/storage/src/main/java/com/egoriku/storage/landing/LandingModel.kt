package com.egoriku.storage.landing

import com.egoriku.core.models.ILandingModel
import com.egoriku.core.models.ITeamMemberModel

data class LandingModel(
        override val aboutInfo: String,
        override val quote: String,
        override val teamMembers: List<ITeamMemberModel>
) : ILandingModel


data class TeamMemberModel(
        override val profileImage: String,
        override val name: String,
        override val skills: String
) : ITeamMemberModel