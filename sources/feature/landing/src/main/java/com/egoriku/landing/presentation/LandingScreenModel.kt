package com.egoriku.landing.presentation

import com.egoriku.landing.domain.model.LandingModel

class LandingScreenModel(
        val loadState: LoadState = LoadState.NONE,
        val landingModel: LandingModel? = null
) {

    fun isEmpty() = landingModel == null
}

// TODO use sealed classes
enum class LoadState {
    NONE,
    PROGRESS,
    ERROR_LOADING,
}