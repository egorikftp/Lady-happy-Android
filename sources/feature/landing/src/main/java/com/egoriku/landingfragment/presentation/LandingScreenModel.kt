package com.egoriku.landingfragment.presentation

import com.egoriku.landingfragment.domain.model.LandingModel

class LandingScreenModel {

    var loadState: LoadState = LoadState.NONE

    var landingModel: LandingModel? = null

    fun isEmpty() = landingModel == null
}

enum class LoadState {
    NONE,
    PROGRESS,
    ERROR_LOADING,
}