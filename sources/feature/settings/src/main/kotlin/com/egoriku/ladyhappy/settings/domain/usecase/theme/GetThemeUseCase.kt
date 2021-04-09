package com.egoriku.ladyhappy.settings.domain.usecase.theme

import com.egoriku.ladyhappy.core.IAppPreferences
import com.egoriku.ladyhappy.core.IDispatchers
import com.egoriku.ladyhappy.core.sharedmodel.Theme
import com.egoriku.ladyhappy.core.sharedmodel.themeFromStorageKey
import com.egoriku.ladyhappy.extensions.hasQ
import com.egoriku.ladyhappy.network.usecase.UseCase

class GetThemeUseCase(
        private val preferences: IAppPreferences,
        dispatchers: IDispatchers
) : UseCase<Unit, Theme>(dispatchers.io) {

    override suspend fun execute(parameters: Unit): Theme {
        val selectedTheme = preferences.selectedTheme
        return themeFromStorageKey(selectedTheme)
                ?: when {
                    hasQ() -> Theme.SYSTEM
                    else -> Theme.BATTERY_SAVER
                }
    }
}