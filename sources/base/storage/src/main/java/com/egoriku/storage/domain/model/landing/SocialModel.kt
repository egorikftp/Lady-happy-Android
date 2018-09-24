package com.egoriku.storage.domain.model.landing

import com.egoriku.core.model.ISocialModel

data class SocialModel(
        override val socialUrl: String,
        override val type: String
) : ISocialModel