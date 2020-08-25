package com.egoriku.mainscreen.presentation.components.inAppUpdates

import com.google.android.play.core.appupdate.AppUpdateInfo

sealed class InAppUpdateEvent {
    data class ToastEvent(val message: String) : InAppUpdateEvent()
    data class StartUpdateEvent(val updateInfo: AppUpdateInfo, val immediate: Boolean) : InAppUpdateEvent()
}