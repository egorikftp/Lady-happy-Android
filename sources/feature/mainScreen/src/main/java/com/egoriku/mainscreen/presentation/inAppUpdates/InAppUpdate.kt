package com.egoriku.mainscreen.presentation.inAppUpdates

import android.app.Activity
import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

private const val IN_APP_UPDATE_REQUEST_CODE = 90

class InAppUpdate(context: Context) {

    private val appUpdateManager = AppUpdateManagerFactory.create(context)

    fun init(activity: Activity) {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.FLEXIBLE,
                        activity,
                        IN_APP_UPDATE_REQUEST_CODE
                )
            }
        }
    }
}