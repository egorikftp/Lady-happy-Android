package com.egoriku.ladyhappy.mainscreen.presentation.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.egoriku.ladyhappy.core.sharedmodel.Theme
import com.egoriku.ladyhappy.mainscreen.domain.usecase.ObserveThemeModeUseCase
import com.egoriku.ladyhappy.network.successOr
import kotlinx.coroutines.flow.collect

interface IThemedActivityDelegate {

    val theme: LiveData<Theme>
}

class ThemedActivityDelegate(
    private val observeThemeUseCase: ObserveThemeModeUseCase
) : IThemedActivityDelegate {

    override val theme: LiveData<Theme> = liveData {
        observeThemeUseCase(Unit).collect {
            emit(it.successOr(Theme.SYSTEM))
        }
    }
}
