package com.egoriku.ladyhappy.settings.domain.usecase.theme

import com.egoriku.core.IAppPreferences
import com.egoriku.core.IDispatchers
import com.egoriku.core.sharedmodel.Theme
import com.egoriku.core.sharedmodel.themeFromStorageKey
import com.egoriku.extensions.hasQ
import com.egoriku.network.usecase.UseCase

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