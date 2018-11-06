package com.egoriku.storage.repository.landing

import com.egoriku.core.model.ILandingModel
import com.egoriku.core.model.IQuotesModel
import com.egoriku.core.model.ISocialModel
import com.egoriku.core.model.ITeamMemberModel
import com.egoriku.core.repository.ILandingRepository
import com.egoriku.network.data.entities.landing.LandingEntity
import com.egoriku.network.data.entities.landing.QuotesEntity
import com.egoriku.network.data.entities.landing.SocialEntity
import com.egoriku.network.data.entities.landing.TeamMemberEntity
import com.egoriku.network.datasource.LandingDataSource
import com.egoriku.network.datasource.LandingDataSourceExperimental
import com.egoriku.storage.common.Constants.EMPTY
import com.egoriku.storage.domain.model.landing.LandingModel
import com.egoriku.storage.domain.model.landing.QuotesModel
import com.egoriku.storage.domain.model.landing.SocialModel
import com.egoriku.storage.domain.model.landing.TeamMemberModel
import io.reactivex.Observable

class LandingRepository(private val landingDataSource: LandingDataSource, private val landingDataSourceEx: LandingDataSourceExperimental) : ILandingRepository {

    override fun getLandingInfo(): Observable<ILandingModel> {
        return landingDataSource.downloadLanding()
                .map(::transform)
    }

    override suspend fun getLandingEx(): ILandingModel {
        val landing = landingDataSourceEx.downloadLanding()
        return transform(landing)
    }

    private fun transform(entity: LandingEntity): ILandingModel =
            LandingModel(
                    aboutInfo = entity.aboutInfo ?: EMPTY,
                    quotes = transformQuotes(entity.quotes),
                    teamMembers = transformMember(entity.teamMembers)
            )

    private fun transformMember(teamMembers: List<TeamMemberEntity>?): List<ITeamMemberModel> {
        return teamMembers?.map {
            TeamMemberModel(
                    profileImage = it.personImageUrl ?: EMPTY,
                    name = it.name ?: EMPTY,
                    skills = it.skills ?: EMPTY,
                    socialLinks = transformSocialLinks(it.socialLinks)
            )
        } ?: emptyList()
    }

    private fun transformSocialLinks(socialLinks: List<SocialEntity>?): List<ISocialModel> {
        return socialLinks?.map {
            SocialModel(
                    socialUrl = it.url ?: EMPTY,
                    type = it.type ?: EMPTY
            )

        } ?: emptyList()
    }

    private fun transformQuotes(quotes: List<QuotesEntity>?): List<IQuotesModel> {
        return quotes?.map {
            QuotesModel(
                    quote = it.quote ?: EMPTY,
                    author = it.author ?: EMPTY
            )
        } ?: emptyList()
    }
}