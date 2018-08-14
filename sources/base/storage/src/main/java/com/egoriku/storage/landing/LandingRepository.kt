package com.egoriku.storage.landing

import com.egoriku.core.models.ILandingModel
import com.egoriku.core.repository.ILandingRepository
import com.egoriku.network.landing.LandingDataSource
import com.egoriku.network.landing.entity.LandingEntity
import com.egoriku.storage.common.Constants.EMPTY
import io.reactivex.Observable

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository {

    override fun getLandingInfo(): Observable<ILandingModel> {
        return landingDataSource.downloadLanding()
                .map(::transform)
    }

    private fun transform(entity: LandingEntity): ILandingModel =
            LandingModel(
                    entity.aboutInfo ?: EMPTY,
                    entity.quote ?: EMPTY
            )
}