package com.egoriku.ladyhappy.mainscreen.presentation.screen.params

import androidx.annotation.StringRes

data class ScreenParams(
    @StringRes
    val screenNameResId: Int,
    val trackingScreenName: String
)