package com.egoriku.ladyhappy.mainscreen.presentation.components.inAppUpdates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.extensions.logD
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.ktx.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InAppUpdateViewModel(updateManager: AppUpdateManager) : ViewModel() {

    private val _events = MutableSharedFlow<InAppUpdateEvent>()
    val events: SharedFlow<InAppUpdateEvent> = _events

    val updateStatus: StateFlow<AppUpdateResult> = updateManager.requestUpdateFlow()
            .catch {
                logD("Update info not available")
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AppUpdateResult.NotAvailable)

    fun shouldLaunchImmediateUpdate(updateInfo: AppUpdateInfo): Boolean {
        with(updateInfo) {
            return isImmediateUpdateAllowed &&
                    (clientVersionStalenessDays ?: 0 > 30 || updatePriority > 4)
        }
    }

    fun invokeUpdate() {
        when (val updateResult = updateStatus.value) {
            AppUpdateResult.NotAvailable -> logD("Update not available")
            is AppUpdateResult.Available -> {
                with(updateResult.updateInfo) {
                    when {
                        shouldLaunchImmediateUpdate(this) -> {
                            viewModelScope.launch {
                                _events.emit(
                                        InAppUpdateEvent.StartUpdateEvent(
                                                updateInfo = updateResult.updateInfo,
                                                immediate = true
                                        )
                                )
                            }
                        }
                        isFlexibleUpdateAllowed -> {
                            viewModelScope.launch {
                                _events.emit(
                                        InAppUpdateEvent.StartUpdateEvent(
                                                updateInfo = updateResult.updateInfo,
                                                immediate = false
                                        )
                                )
                            }
                        }
                        else -> false
                    }
                }
            }
            is AppUpdateResult.InProgress -> viewModelScope.launch {
                _events.emit(InAppUpdateEvent.ToastEvent("Update already in progress"))
            }
            is AppUpdateResult.Downloaded -> viewModelScope.launch {
                updateResult.completeUpdate()
            }
        }
    }
}