package com.egoriku.storage

import com.egoriku.core.models.ILandingModel

data class LandingModel(
        val aboutInfo: String
) : ILandingModel {

    override fun aboutInfo() = aboutInfo
}