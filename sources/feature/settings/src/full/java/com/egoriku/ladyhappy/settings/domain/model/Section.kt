package com.egoriku.ladyhappy.settings.domain.model

import com.egoriku.ladyhappy.auth.model.UserLoginState

sealed class Section(open val position: Int) {

    data class Login(
            val state: UserLoginState
    ) : Section(position = 0)

    data class AvailableFeatures(
            val features: List<Feature>
    ) : Section(position = 1)
}

sealed class Feature(
        open val isAvailable: Boolean
) {

    class PublishPosts(
            override val isAvailable: Boolean
    ) : Feature(isAvailable)

    class Stub : Feature(isAvailable = true)
}