package com.egoriku.mainscreen.domain.usecase

import android.os.Build
import com.egoriku.core.IAppPreferences
import com.egoriku.core.IDispatchers
import com.egoriku.core.sharedmodel.Theme
import com.egoriku.core.sharedmodel.themeFromStorageKey
import com.egoriku.network.ResultOf
import com.egoriku.network.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveThemeModeUseCase(
        private val preferences: IAppPreferences,
        dispatchers: IDispatchers
) : FlowUseCase<Unit, Theme>(dispatchers.default) {

    override fun execute(parameters: Unit): Flow<ResultOf<Theme>> =
            preferences.observableSelectedTheme.map {
                val theme = themeFromStorageKey(it)
                        ?: when {
                            Build.VERSION.SDK_INT >= 29 -> Theme.SYSTEM
                            else -> Theme.BATTERY_SAVER
                        }
                ResultOf.Success(theme)
            }
}
