package com.egoriku.ladyhappy.core

import kotlinx.coroutines.flow.Flow

interface IAppPreferences {

    var launchCount: Int
    var lastAskForReview: Long
    var selectedTheme: String

    val selectedThemeFlow: Flow<String>
}