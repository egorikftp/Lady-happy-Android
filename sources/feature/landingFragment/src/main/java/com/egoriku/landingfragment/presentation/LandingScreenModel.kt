package com.egoriku.landingfragment.presentation

import com.egoriku.core.model.ILandingModel

class LandingScreenModel {

    var landingModel: ILandingModel? = null

    fun isEmpty() = landingModel == null
}