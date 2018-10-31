package com.egoriku.storage.domain.model.landing

import com.egoriku.core.model.ILandingModel
import com.egoriku.core.model.IQuotesModel
import com.egoriku.core.model.ITeamMemberModel

data class LandingModel(
        override val aboutInfo: String,
        override val quotes: List<IQuotesModel>,
        override val teamMembers: List<ITeamMemberModel>
) : ILandingModel