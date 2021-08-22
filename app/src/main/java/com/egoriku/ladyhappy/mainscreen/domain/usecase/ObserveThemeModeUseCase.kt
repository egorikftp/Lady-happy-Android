package com.egoriku.ladyhappy.mainscreen.domain.usecase

import com.egoriku.ladyhappy.core.IAppPreferences
import com.egoriku.ladyhappy.core.IDispatchers
import com.egoriku.ladyhappy.core.sharedmodel.Theme
import com.egoriku.ladyhappy.core.sharedmodel.themeFromStorageKey
import com.egoriku.ladyhappy.extensions.hasQ
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.usecase.FlowResultUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveThemeModeUseCase(
    private val preferences: IAppPreferences,
    dispatchers: IDispatchers
) : FlowResultUseCase<Unit, Theme>(dispatchers.default) {

    override fun execute(parameters: Unit): Flow<ResultOf<Theme>> =
        preferences.selectedThemeFlow.map {
            val theme = themeFromStorageKey(it)
                ?: when {
                    hasQ() -> Theme.SYSTEM
                    else -> Theme.BATTERY_SAVER
                }
            ResultOf.Success(theme)
        }
}