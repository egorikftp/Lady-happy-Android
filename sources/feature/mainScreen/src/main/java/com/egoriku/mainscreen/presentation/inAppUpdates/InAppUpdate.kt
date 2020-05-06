package com.egoriku.mainscreen.presentation.inAppUpdates

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.egoriku.ladyhappy.extensions.logDm
import com.egoriku.mainscreen.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

const val UPDATE_FLEXIBLE_REQUEST_CODE = 6708

class InAppUpdate(
        private val activity: AppCompatActivity,
        private val parentView: View
) : LifecycleObserver {

    init {
        activity.lifecycle.addObserver(this)
    }

    private val appUpdateManager = AppUpdateManagerFactory.create(activity.applicationContext)

    private var installStateUpdatedListener: InstallStateUpdatedListener = InstallStateUpdatedListener { installState ->
        when (installState.installStatus()) {
            InstallStatus.DOWNLOADED -> {
                logDm("InstallStatus.DOWNLOADED")
                showDownloadFinishedSnackBar()
            }
            InstallStatus.FAILED -> {
                logDm("InstallStatus.DOWNLOADED")
                Snackbar.make(
                        parentView,
                        activity.getString(R.string.in_app_update_download_failed),
                        Snackbar.LENGTH_LONG
                ).apply {
                    anchorView = parentView
                    setAction(activity.getString(R.string.in_app_update_retry)) {
                        init()
                    }
                    show()
                }
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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun init() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            when (appUpdateInfo.updateAvailability()) {
                UpdateAvailability.UPDATE_AVAILABLE -> {
                    logDm("ON_CREATE: UpdateAvailability.UPDATE_AVAILABLE")
                    when {
                        appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE) -> {
                            logDm("AppUpdateType.FLEXIBLE")
                            appUpdateManager.registerListener(installStateUpdatedListener)

                            appUpdateManager.startUpdateFlowForResult(
                                    appUpdateInfo,
                                    AppUpdateType.FLEXIBLE,
                                    activity,
                                    UPDATE_FLEXIBLE_REQUEST_CODE
                            )
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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun checkStatus() {
        appUpdateManager.registerListener(installStateUpdatedListener)

        appUpdateManager.appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    when (appUpdateInfo.updateAvailability()) {
                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            logDm("ON_RESUME: UpdateAvailability.UPDATE_AVAILABLE")
                            if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                                    logDm("ON_RESUME: InstallStatus.DOWNLOADED")
                                    showDownloadFinishedSnackBar()
                                }
                            }
                        }

                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                            logDm("ON_RESUME: UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS")

                            /*if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                                appUpdateManager.startUpdateFlowForResult(
                                        appUpdateInfo,
                                        AppUpdateType.FLEXIBLE,
                                        activity,
                                        UPDATE_FLEXIBLE_REQUEST_CODE
                                )
                            }*/
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

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun unsubscribe() = appUpdateManager.unregisterListener(installStateUpdatedListener)

    private fun showDownloadFinishedSnackBar() {
        Snackbar.make(
                parentView,
                activity.getString(R.string.in_app_update_download_finished),
                Snackbar.LENGTH_INDEFINITE
        ).apply {
            anchorView = parentView
            setAction(activity.getString(R.string.in_app_update_restart)) {
                unsubscribe()

                appUpdateManager.completeUpdate()
            }
            show()
        }
    }
}