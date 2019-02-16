package com.egoriku.landingfragment.data.mapper

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.landingfragment.data.entity.LandingEntity
import com.egoriku.landingfragment.data.entity.QuotesEntity
import com.egoriku.landingfragment.data.entity.SocialEntity
import com.egoriku.landingfragment.data.entity.TeamMemberEntity
import com.egoriku.landingfragment.domain.model.LandingModel
import com.egoriku.landingfragment.domain.model.QuotesModel
import com.egoriku.landingfragment.domain.model.SocialModel
import com.egoriku.landingfragment.domain.model.TeamMemberModel
import com.egoriku.network.firestore.Result

object LandingMapper {
    fun transformResult(entity: LandingEntity): Result<LandingModel> =
            Result.Success(com.egoriku.landingfragment.domain.model.LandingModel(
                    aboutInfo = entity.aboutInfo ?: EMPTY,
                    quotes = transformQuotes(entity.quotes),
                    teamMembers = transformMember(entity.teamMembers)
            ))

    private fun transformMember(teamMembers: List<TeamMemberEntity>?): List<TeamMemberModel> {
        return teamMembers?.map {
            com.egoriku.landingfragment.domain.model.TeamMemberModel(
                    profileImage = it.personImageUrl ?: EMPTY,
                    name = it.name ?: EMPTY,
                    skills = it.skills ?: EMPTY,
                    socialLinks = transformSocialLinks(it.socialLinks)
            )
        } ?: emptyList()
    }

    private fun transformSocialLinks(socialLinks: List<SocialEntity>?): List<SocialModel> {
        return socialLinks?.map {
            com.egoriku.landingfragment.domain.model.SocialModel(
                    socialUrl = it.url ?: EMPTY,
                    type = it.type ?: EMPTY
            )

        } ?: emptyList()
    }

    private fun transformQuotes(quotes: List<QuotesEntity>?): List<QuotesModel> {
        return quotes?.map {
            com.egoriku.landingfragment.domain.model.QuotesModel(
                    quote = it.quote ?: EMPTY,
                    author = it.author ?: EMPTY
            )
        } ?: emptyList()
    }
}