package com.egoriku.ladyhappy.landing.data.mapper

import com.egoriku.ladyhappy.landing.data.entity.LandingEntity
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.ladyhappy.landing.domain.model.QuotesModel
import com.egoriku.ladyhappy.landing.domain.model.SocialModel
import com.egoriku.ladyhappy.landing.domain.model.TeamMemberModel

class LandingEntityMapper : (LandingEntity) -> LandingModel {

    override fun invoke(entity: LandingEntity) = LandingModel(
            aboutInfo = entity.aboutInfo,
            quotes = entity.quotes.map { quote ->
                QuotesModel(quote = quote.quote, author = quote.author)
            },
            teamMembers = entity.teamMembers.map { teamMember ->
                TeamMemberModel(
                        profileImage = teamMember.personImageUrl,
                        name = teamMember.name,
                        skills = teamMember.skills,
                        socialLinks = teamMember.socialLinks.map { social ->
                            SocialModel(socialUrl = social.url, type = social.type)
                        }
                )
            }
    )
}