package com.egoriku.storage.repository.landing

import com.egoriku.core.model.ILandingModel
import com.egoriku.core.model.IQuotesModel
import com.egoriku.core.model.ISocialModel
import com.egoriku.core.model.ITeamMemberModel
import com.egoriku.network.data.entities.landing.LandingEntity
import com.egoriku.network.data.entities.landing.QuotesEntity
import com.egoriku.network.data.entities.landing.SocialEntity
import com.egoriku.network.data.entities.landing.TeamMemberEntity
import com.egoriku.core.firestore.Result
import com.egoriku.storage.common.Constants
import com.egoriku.storage.domain.model.landing.LandingModel
import com.egoriku.storage.domain.model.landing.QuotesModel
import com.egoriku.storage.domain.model.landing.SocialModel
import com.egoriku.storage.domain.model.landing.TeamMemberModel

object Mapper {

    fun transform(entity: LandingEntity): ILandingModel =
            LandingModel(
                    aboutInfo = entity.aboutInfo ?: Constants.EMPTY,
                    quotes = transformQuotes(entity.quotes),
                    teamMembers = transformMember(entity.teamMembers)
            )

    fun transformResult(entity: LandingEntity): Result<ILandingModel> =
            Result.Success(LandingModel(
                    aboutInfo = entity.aboutInfo ?: Constants.EMPTY,
                    quotes = transformQuotes(entity.quotes),
                    teamMembers = transformMember(entity.teamMembers)
            ))

    private fun transformMember(teamMembers: List<TeamMemberEntity>?): List<ITeamMemberModel> {
        return teamMembers?.map {
            TeamMemberModel(
                    profileImage = it.personImageUrl ?: Constants.EMPTY,
                    name = it.name ?: Constants.EMPTY,
                    skills = it.skills ?: Constants.EMPTY,
                    socialLinks = transformSocialLinks(it.socialLinks)
            )
        } ?: emptyList()
    }

    private fun transformSocialLinks(socialLinks: List<SocialEntity>?): List<ISocialModel> {
        return socialLinks?.map {
            SocialModel(
                    socialUrl = it.url ?: Constants.EMPTY,
                    type = it.type ?: Constants.EMPTY
            )

        } ?: emptyList()
    }

    private fun transformQuotes(quotes: List<QuotesEntity>?): List<IQuotesModel> {
        return quotes?.map {
            QuotesModel(
                    quote = it.quote ?: Constants.EMPTY,
                    author = it.author ?: Constants.EMPTY
            )
        } ?: emptyList()
    }
}