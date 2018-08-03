package com.egoriku.core.repository

import com.egoriku.core.models.ILandingModel
import io.reactivex.Observable

interface ILandingRepository {

    fun getLandingInfo(): Observable<ILandingModel>
}