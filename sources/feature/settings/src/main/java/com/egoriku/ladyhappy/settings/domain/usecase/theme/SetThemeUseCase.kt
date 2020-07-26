package com.egoriku.ladyhappy.settings.domain.usecase.theme

import com.egoriku.core.IAppPreferences
import com.egoriku.core.IDispatchers
import com.egoriku.core.sharedmodel.Theme
import com.egoriku.network.usecase.UseCase

class SetThemeUseCase(
        private val preferences: IAppPreferences,
        dispatcher: IDispatchers
) : UseCase<Theme, Unit>(dispatcher.io) {

    override suspend fun execute(parameters: Theme) {
        preferences.selectedTheme = parameters.storageKey
    }
}
