package com.egoriku.landingfragment.presentation

import com.egoriku.core.model.ILandingModel

class LandingScreenModel {

    var loadState: LoadState = LoadState.NONE

    var landingModel: ILandingModel? = null

    fun isEmpty() = landingModel == null
}

enum class LoadState {
    NONE,
    PROGRESS,
    ERROR_LOADING,
}