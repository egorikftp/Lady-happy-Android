package com.egoriku.landing.data.mapper

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.landing.data.entity.LandingEntity
import com.egoriku.landing.data.entity.QuotesEntity
import com.egoriku.landing.data.entity.SocialEntity
import com.egoriku.landing.data.entity.TeamMemberEntity
import com.egoriku.landing.domain.model.LandingModel
import com.egoriku.landing.domain.model.QuotesModel
import com.egoriku.landing.domain.model.SocialModel
import com.egoriku.landing.domain.model.TeamMemberModel
import com.egoriku.network.Result

object LandingMapper {
    fun transformResult(entity: LandingEntity): Result<LandingModel> =
            Result.Success(LandingModel(
                    aboutInfo = entity.aboutInfo ?: EMPTY,
                    quotes = transformQuotes(entity.quotes),
                    teamMembers = transformMember(entity.teamMembers)
            ))

    private fun transformMember(teamMembers: List<TeamMemberEntity>?): List<TeamMemberModel> {
        return teamMembers?.map {
            TeamMemberModel(
                    profileImage = it.personImageUrl ?: EMPTY,
                    name = it.name ?: EMPTY,
                    skills = it.skills ?: EMPTY,
                    socialLinks = transformSocialLinks(it.socialLinks)
            )
        } ?: emptyList()
    }

    private fun transformSocialLinks(socialLinks: List<SocialEntity>?): List<SocialModel> {
        return socialLinks?.map {
            SocialModel(
                    socialUrl = it.url ?: EMPTY,
                    type = it.type ?: EMPTY
            )

        } ?: emptyList()
    }

    private fun transformQuotes(quotes: List<QuotesEntity>?): List<QuotesModel> {
        return quotes?.map {
            QuotesModel(
                    quote = it.quote ?: EMPTY,
                    author = it.author ?: EMPTY
            )
        } ?: emptyList()
    }
}