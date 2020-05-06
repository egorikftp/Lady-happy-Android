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
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun init() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            when (appUpdateInfo.updateAvailability()) {
                UpdateAvailability.UPDATE_AVAILABLE -> {
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
            }
        }.addOnFailureListener {
            logDm("addOnFailureListener $it")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun checkStatus() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            when (appUpdateInfo.updateAvailability()) {
                UpdateAvailability.UPDATE_AVAILABLE -> {
                    if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                        logDm("AppUpdateType.FLEXIBLE 1")
                        if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                            showDownloadFinishedSnackBar()
                        }
                    }
                }

               /* UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                    logDm("DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS")
                    if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                        appUpdateManager.startUpdateFlowForResult(
                                appUpdateInfo,
                                AppUpdateType.FLEXIBLE,
                                activity,
                                UPDATE_FLEXIBLE_REQUEST_CODE
                        )
                    }
                }*/
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