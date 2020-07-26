package com.egoriku.core

import kotlinx.coroutines.flow.Flow

interface IAppPreferences {

    var selectedTheme: String

    var observableSelectedTheme: Flow<String>
}