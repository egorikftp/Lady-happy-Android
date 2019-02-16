package com.egoriku.landing.presentation

import com.egoriku.landing.domain.model.LandingModel

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