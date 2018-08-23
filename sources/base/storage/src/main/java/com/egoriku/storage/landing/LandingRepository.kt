package com.egoriku.storage.landing

import com.egoriku.core.models.ILandingModel
import com.egoriku.core.models.ITeamMemberModel
import com.egoriku.core.repository.ILandingRepository
import com.egoriku.network.landing.LandingDataSource
import com.egoriku.network.landing.entity.LandingEntity
import com.egoriku.network.landing.entity.TeamMemberEntity
import com.egoriku.storage.common.Constants.EMPTY
import io.reactivex.Observable

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository {

    override fun getLandingInfo(): Observable<ILandingModel> {
        return landingDataSource.downloadLanding()
                .map(::transform)
    }

    private fun transform(entity: LandingEntity): ILandingModel =
            LandingModel(
                    aboutInfo = entity.aboutInfo ?: EMPTY,
                    quote = entity.quote ?: EMPTY,
                    teamMembers = transformMember(entity.teamMembers)
            )

    private fun transformMember(teamMembers: List<TeamMemberEntity>?): List<ITeamMemberModel> {
        return teamMembers?.map {
            TeamMemberModel(
                    profileImage = it.personImageUrl ?: EMPTY,
                    name = it.name ?: EMPTY,
                    skills = it.skills ?: EMPTY
            )
        } ?: emptyList()
    }
}