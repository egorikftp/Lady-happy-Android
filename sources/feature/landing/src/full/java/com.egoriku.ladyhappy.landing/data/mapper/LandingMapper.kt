package com.egoriku.ladyhappy.landing.data.mapper

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.landing.data.entity.LandingEntity
import com.egoriku.ladyhappy.landing.data.entity.QuotesEntity
import com.egoriku.ladyhappy.landing.data.entity.SocialEntity
import com.egoriku.ladyhappy.landing.data.entity.TeamMemberEntity
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.ladyhappy.landing.domain.model.QuotesModel
import com.egoriku.ladyhappy.landing.domain.model.SocialModel
import com.egoriku.ladyhappy.landing.domain.model.TeamMemberModel
import com.egoriku.network.ResultOf

object LandingMapper {
    fun transformResult(entity: LandingEntity): ResultOf<LandingModel> =
            ResultOf.Success(LandingModel(
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