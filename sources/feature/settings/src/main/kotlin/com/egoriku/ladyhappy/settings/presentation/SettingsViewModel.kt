package com.egoriku.ladyhappy.settings.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.navigation.screen.Screen
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.domain.usecase.IAuthenticationUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.ISectionsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

class SettingsViewModel(
    private val router: IRouter,
    private val sectionsUseCase: ISectionsUseCase,
    private val authenticationUseCase: IAuthenticationUseCase
) : ViewModel() {

    private val cache = ConcurrentHashMap<Int, Section>()
    private val _screenData = MutableLiveData<Map<Int, Section>>()

    val screenData: LiveData<Map<Int, Section>> = _screenData

    init {
        viewModelScope.launch {
            sectionsUseCase.load().collect { updateSection(it) }
        }

        viewModelScope.launch {
            authenticationUseCase.subscribeAuthEvents().collect {
                updateSection(it)

                refresh()
            }
        }
    }

    fun logOut() = authenticationUseCase.logout()

    private fun updateSection(values: Section) {
        cache[values.position] = values
        cache.toSortedMap(compareBy { it })

        _screenData.value = cache
    }

    fun navigateTo(screen: Screen) = router.addScreenFullscreen(screen)

    private fun refresh() {
        viewModelScope.launch {
            sectionsUseCase.refresh().collect {
                updateSection(it)
            }
        }
    }
}