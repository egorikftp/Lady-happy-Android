package com.egoriku.storage.repository.landing

import com.egoriku.core.model.ILandingModel
import com.egoriku.core.model.ISocialModel
import com.egoriku.core.model.ITeamMemberModel

data class LandingModel(
        override val aboutInfo: String,
        override val quote: String,
        override val teamMembers: List<ITeamMemberModel>
) : ILandingModel


data class TeamMemberModel(
        override val profileImage: String,
        override val name: String,
        override val skills: String,
        override val socialLinks: List<ISocialModel>
) : ITeamMemberModel

data class SocialModel(
        override val socialUrl: String,
        override val type: String
) : ISocialModel