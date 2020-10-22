package com.egoriku.ladyhappy.settings.domain.usecase.theme

import com.egoriku.ladyhappy.core.IAppPreferences
import com.egoriku.ladyhappy.core.IDispatchers
import com.egoriku.ladyhappy.core.sharedmodel.Theme
import com.egoriku.ladyhappy.network.usecase.UseCase

class SetThemeUseCase(
        private val preferences: IAppPreferences,
        dispatcher: IDispatchers
) : UseCase<Theme, Unit>(dispatcher.io) {

    override suspend fun execute(parameters: Theme) {
        preferences.selectedTheme = parameters.storageKey
    }
}
