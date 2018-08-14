package com.egoriku.storage.landing

import com.egoriku.core.models.ILandingModel

data class LandingModel(
        private val aboutInfo: String,
        private val quote: String
) : ILandingModel {

    override fun aboutInfo() = aboutInfo
    override fun quote() = quote
}