package com.egoriku.ladyhappy.settings.presentation.dialog.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.egoriku.core.sharedmodel.Theme
import com.egoriku.ladyhappy.settings.domain.usecase.theme.GetAvailableThemesUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.GetThemeUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.theme.SetThemeUseCase
import com.egoriku.network.successOr
import kotlinx.coroutines.launch

class ThemeViewModel(
        private val themeUseCase: GetThemeUseCase,
        private val availableThemesUseCase: GetAvailableThemesUseCase,
        private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {

    val theme: LiveData<Theme> = liveData {
        emit(themeUseCase(Unit).successOr(Theme.SYSTEM))
    }

    val availableThemes: LiveData<List<Theme>> = liveData {
        emit(availableThemesUseCase(Unit).successOr(emptyList()))
    }

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            setThemeUseCase(theme)
        }
    }
}