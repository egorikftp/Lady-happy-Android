package com.egoriku.mainscreen.presentation.inAppUpdates

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egoriku.extensions.Event
import com.egoriku.mainscreen.presentation.inAppUpdates.InAppUpdateState.*
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

const val UPDATE_REQUEST_CODE = 6708

sealed class InAppUpdateState {
    object OnFailed : InAppUpdateState()
    object Downloaded : InAppUpdateState()
    data class RequestFlowUpdate(val updateInfo: AppUpdateInfo) : InAppUpdateState()
}

class InAppUpdate(context: Context) {

    private val appUpdateManager = AppUpdateManagerFactory.create(context)
    private val _status = MutableLiveData<Event<InAppUpdateState>>()

    val status: LiveData<Event<InAppUpdateState>> = _status

    @SuppressLint("SwitchIntDef")
    private val installStateUpdatedListener: InstallStateUpdatedListener = InstallStateUpdatedListener { installState ->
        when (installState.installStatus()) {
            InstallStatus.DOWNLOADED -> _status.value = Event(Downloaded)
            InstallStatus.FAILED -> _status.value = Event(OnFailed)
        }
    }

    @SuppressLint("SwitchIntDef")
    fun checkForUpdates() {
        appUpdateManager.appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    when (appUpdateInfo.updateAvailability()) {
                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                                _status.value = Event(RequestFlowUpdate(appUpdateInfo))
                            }
                        }
                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                            when (InstallStatus.DOWNLOADED) {
                                appUpdateInfo.installStatus() -> _status.value = Event(Downloaded)
                                else -> appUpdateManager.registerListener(installStateUpdatedListener)
                            }
                        }
                    }
                }
    }

    fun startUpdateFlow(activity: Activity, updateInfo: AppUpdateInfo) {
        appUpdateManager.registerListener(installStateUpdatedListener)
        appUpdateManager.startUpdateFlowForResult(
                updateInfo,
                AppUpdateType.FLEXIBLE,
                activity,
                UPDATE_REQUEST_CODE
        )
    }

    fun unsubscribe() {
        appUpdateManager.unregisterListener(installStateUpdatedListener)
    }

    fun completeUpdate() {
        unsubscribe()
        appUpdateManager.completeUpdate()
    }
}