package com.egoriku.mainscreen.presentation.inAppUpdates

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egoriku.ladyhappy.extensions.logDm
import com.egoriku.mainscreen.presentation.inAppUpdates.InAppUpdateState.*
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

const val UPDATE_FLEXIBLE_REQUEST_CODE = 6708

sealed class InAppUpdateState {
    object OnFailed : InAppUpdateState()
    object Downloaded : InAppUpdateState()
    object RequestFlowUpdate : InAppUpdateState()
}

class InAppUpdate(context: Context) {

    private val appUpdateManager = AppUpdateManagerFactory.create(context)
    private val _status = MutableLiveData<InAppUpdateState>()

    private var updateInfo: AppUpdateInfo? = null

    val status: LiveData<InAppUpdateState> = _status

    private val installStateUpdatedListener: InstallStateUpdatedListener = InstallStateUpdatedListener { installState ->
        when (installState.installStatus()) {
            InstallStatus.DOWNLOADED -> {
                logDm("InstallStatus.DOWNLOADED")
                _status.value = Downloaded
            }
            InstallStatus.FAILED -> {
                logDm("InstallStatus.FAILED")
                _status.value = OnFailed
            }
            InstallStatus.CANCELED -> {
                logDm("InstallStatus.CANCELED")
            }
            InstallStatus.DOWNLOADING -> {
                logDm("InstallStatus.DOWNLOADING")
            }
            InstallStatus.INSTALLED -> {
                logDm("InstallStatus.INSTALLED")
            }
            InstallStatus.INSTALLING -> {
                logDm("InstallStatus.INSTALLING")
            }
            InstallStatus.PENDING -> {
                logDm("InstallStatus.PENDING")
            }
            InstallStatus.REQUIRES_UI_INTENT -> {
                logDm("InstallStatus.REQUIRES_UI_INTENT")
            }
            InstallStatus.UNKNOWN -> {
                logDm("InstallStatus.UNKNOWN")
            }
        }
    }

    fun init() {
        logDm("init")
        appUpdateManager.appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    when (appUpdateInfo.updateAvailability()) {
                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            logDm("ON_CREATE: UpdateAvailability.UPDATE_AVAILABLE")
                            when {
                                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE) -> {
                                    logDm("AppUpdateType.FLEXIBLE")
                                    appUpdateManager.registerListener(installStateUpdatedListener)

                                    _status.value = RequestFlowUpdate
                                }
                            }
                        }
                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                            logDm("ON_CREATE: UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS")
                        }
                        UpdateAvailability.UNKNOWN -> {
                            logDm("ON_CREATE: UpdateAvailability.UNKNOWN")
                        }
                        UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                            logDm("ON_CREATE: UpdateAvailability.UPDATE_NOT_AVAILABLE")
                        }
                    }
                }.addOnFailureListener {
                    logDm("addOnFailureListener $it")
                }
    }

    fun checkStatus() {
        logDm("checkStatus")
        appUpdateManager.registerListener(installStateUpdatedListener)

        appUpdateManager.appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    updateInfo = appUpdateInfo

                    when (appUpdateInfo.updateAvailability()) {
                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            logDm("ON_RESUME: UpdateAvailability.UPDATE_AVAILABLE")
                            if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                                    logDm("ON_RESUME: InstallStatus.DOWNLOADED")
                                    _status.value = Downloaded
                                }
                            }
                        }

                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                            logDm("ON_RESUME: UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS")
                        }
                        UpdateAvailability.UNKNOWN -> {
                            logDm("ON_RESUME: UpdateAvailability.UNKNOWN")
                        }
                        UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                            logDm("ON_RESUME: UpdateAvailability.UPDATE_NOT_AVAILABLE")
                        }
                    }
                }
    }

    fun startUpdateFlow(activity: Activity) {
        logDm("startUpdateFlow")
        updateInfo ?: return

        appUpdateManager.startUpdateFlowForResult(
                updateInfo,
                AppUpdateType.FLEXIBLE,
                activity,
                UPDATE_FLEXIBLE_REQUEST_CODE
        )
    }

    fun unsubscribe() {
        logDm("unsubscribe")
        appUpdateManager.unregisterListener(installStateUpdatedListener)
    }

    fun completeUpdate() {
        logDm("completeUpdate")
        unsubscribe()
        appUpdateManager.completeUpdate()
    }
}