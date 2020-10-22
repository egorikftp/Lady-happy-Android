package com.egoriku.ladyhappy.settings.domain.model

import androidx.annotation.DrawableRes
import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.domain.model.setting.SettingItem

sealed class Section(open val position: Int) {

    data class Login(
            val state: UserLoginState
    ) : Section(position = 0)

    data class AvailableFeatures(
            val features: List<Feature>
    ) : Section(position = 1)

    data class Settings(
            val setting: List<SettingItem>
    ) : Section(position = 2)
}

sealed class Feature(
        open val isAvailable: Boolean,

        @DrawableRes
        open val iconResId: Int
) {

    class PublishPosts(
            override val isAvailable: Boolean
    ) : Feature(
            isAvailable = isAvailable,
            iconResId = R.drawable.ic_create
    )

    class Stub : Feature(
            isAvailable = true,
            iconResId = R.drawable.bg_feature_stub
    )
}