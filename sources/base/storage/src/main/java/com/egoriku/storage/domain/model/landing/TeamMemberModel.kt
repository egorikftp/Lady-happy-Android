package com.egoriku.storage.domain.model.landing

import com.egoriku.core.model.ISocialModel
import com.egoriku.core.model.ITeamMemberModel

data class TeamMemberModel(
        override val profileImage: String,
        override val name: String,
        override val skills: String,
        override val socialLinks: List<ISocialModel>
) : ITeamMemberModel