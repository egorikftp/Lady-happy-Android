package com.egoriku.mainscreen.presentation.screen.base

import androidx.annotation.StringRes

interface IScreen {

    val trackingKey: String

    @get:StringRes
    val pageTitle: Int
}