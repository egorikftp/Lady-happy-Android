package com.egoriku.ladyhappy.settings.domain.usecase.theme

import android.os.Build
import com.egoriku.core.IAppPreferences
import com.egoriku.core.IDispatchers
import com.egoriku.core.sharedmodel.Theme
import com.egoriku.core.sharedmodel.themeFromStorageKey
import com.egoriku.network.usecase.UseCase

class GetThemeUseCase(
        private val preferences: IAppPreferences,
        dispatchers: IDispatchers
) : UseCase<Unit, Theme>(dispatchers.io) {

    override suspend fun execute(parameters: Unit): Theme {

        val selectedTheme = preferences.selectedTheme
        return themeFromStorageKey(selectedTheme)
                ?: when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> Theme.SYSTEM
                    else -> Theme.BATTERY_SAVER
                }
    }
}