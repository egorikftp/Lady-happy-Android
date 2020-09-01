package com.egoriku.mainscreen.presentation.components.inAppUpdates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.egoriku.extensions.logD
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.ktx.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class InAppUpdateViewModel(updateManager: AppUpdateManager) : ViewModel() {

    private val _events = BroadcastChannel<InAppUpdateEvent>(Channel.BUFFERED)

    val updateStatus = updateManager.requestUpdateFlow()
            .catch {
                logD("Update info not available")
            }
            .asLiveData()

    val events = _events.asFlow()

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
                                _events.send(InAppUpdateEvent.StartUpdateEvent(updateResult.updateInfo, true))
                            }
                        }
                        isFlexibleUpdateAllowed -> {
                            viewModelScope.launch {
                                _events.send(InAppUpdateEvent.StartUpdateEvent(updateResult.updateInfo, false))
                            }
                        }
                        else -> false
                    }
                }
            }
            is AppUpdateResult.InProgress -> viewModelScope.launch {
                _events.send(InAppUpdateEvent.ToastEvent("Update already in progress"))
            }
            is AppUpdateResult.Downloaded -> viewModelScope.launch {
                updateResult.completeUpdate()
            }
        }
    }
}