package com.egoriku.ladyhappy.settings.domain.usecase.theme

import com.egoriku.core.IDispatchers
import com.egoriku.core.sharedmodel.Theme
import com.egoriku.extensions.hasQ
import com.egoriku.network.usecase.UseCase

class GetAvailableThemesUseCase(
        dispatcher: IDispatchers
) : UseCase<Unit, List<Theme>>(dispatcher.mainImmediate) {

    override suspend fun execute(parameters: Unit): List<Theme> = when {
        hasQ() -> listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM)
        else -> listOf(Theme.LIGHT, Theme.DARK, Theme.BATTERY_SAVER)
    }
}
