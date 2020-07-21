package com.egoriku.ladyhappy.settings.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.navigation.screen.Screen
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.domain.usecase.AuthenticationUseCase
import com.egoriku.ladyhappy.settings.domain.usecase.SectionsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

class SettingsViewModel(
        private val router: IRouter,
        private val sectionsUseCase: SectionsUseCase,
        private val authenticationUseCase: AuthenticationUseCase
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

    fun navigateTo(screen: Screen, containerId: Int) = router.addScreenWithContainerId(screen, containerId)

    private fun refresh() {
        viewModelScope.launch {
            sectionsUseCase.refresh().collect {
                updateSection(it)
            }
        }
    }
}