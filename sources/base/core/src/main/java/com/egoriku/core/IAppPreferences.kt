package com.egoriku.core

import kotlinx.coroutines.flow.Flow

interface IAppPreferences {

    var launchCount: Int

    var lastAskForReview: Long

    var selectedTheme: String

    var observableSelectedTheme: Flow<String>
}