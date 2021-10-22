package com.egoriku.ladyhappy.core.splitinstall

import com.egoriku.ladyhappy.extensions.logD
import com.egoriku.ladyhappy.extensions.logE
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

@Suppress("OptionalWhenBraces")
class SplitInstallHelper(
    private val splitInstallManager: SplitInstallManager
) : ISplitInstallHelper {

    override fun installModule(moduleName: String, moduleInstallListener: ModuleInstallListener) {
        if (splitInstallManager.installedModules.contains(moduleName)) {
            logD("Split install: $moduleName already installed")
            moduleInstallListener.onSuccess()
        } else {
            val request = SplitInstallRequest.newBuilder().addModule(moduleName).build()

            var mySessionId = 0

            val listener = object : SplitInstallStateUpdatedListener {

                override fun onStateUpdate(state: SplitInstallSessionState) {
                    if (state.status() == SplitInstallSessionStatus.FAILED) {
                        moduleInstallListener.onFailure()
                        splitInstallManager.unregisterListener(this)
                    } else if (state.sessionId() == mySessionId) {
                        when (state.status()) {
                            // The request has been accepted and the download should start soon.
                            SplitInstallSessionStatus.PENDING -> {
                                logD("Split install: $moduleName pending")
                            }
                            // The download requires user confirmation.
                            // This is most likely due to the size of the download being larger than 10 MB.
                            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                                logD("Split install: $moduleName requires user confirmation")
                                moduleInstallListener.onRequiresUserConfirmation(state)
                            }
                            // Download is in progress.
                            SplitInstallSessionStatus.DOWNLOADING -> {
                                val totalBytes = state.totalBytesToDownload()
                                val progress = state.bytesDownloaded()
                                logD("Split install: $moduleName downloading $progress/$totalBytes bytes")
                                moduleInstallListener.onDownloadInProgress(progress, totalBytes)
                            }
                            // The device has downloaded the module but installation has no yet begun.
                            SplitInstallSessionStatus.DOWNLOADED -> {
                                logD("Split install: $moduleName downloaded")
                            }
                            // The device is currently installing the module.
                            SplitInstallSessionStatus.INSTALLING -> {
                                logD("Split install: $moduleName installing")
                                moduleInstallListener.onInstalling()
                            }
                            // The module is installed on the device.
                            SplitInstallSessionStatus.INSTALLED -> {
                                logD("Split install: $moduleName installed")
                                moduleInstallListener.onSuccess()
                                splitInstallManager.unregisterListener(this)
                            }
                            // The request failed before the module was installed on the device.
                            SplitInstallSessionStatus.FAILED -> {
                                logD("Split install: $moduleName failed: " + state.errorCode())
                                moduleInstallListener.onFailure()
                                splitInstallManager.unregisterListener(this)
                            }
                            // The device is in the process of cancelling the request.
                            SplitInstallSessionStatus.CANCELING -> {
                                logD("Split install: $moduleName cancelling")
                            }
                            // The request has been cancelled.
                            SplitInstallSessionStatus.CANCELED -> {
                                logD("Split install: $moduleName canceled")
                                moduleInstallListener.onFailure()
                                splitInstallManager.unregisterListener(this)
                            }
                            SplitInstallSessionStatus.UNKNOWN -> {
                                logD("Split install: $moduleName unknown status")
                                moduleInstallListener.onFailure()
                                splitInstallManager.unregisterListener(this)
                            }
                        }
                    }
                }
            }

            splitInstallManager.registerListener(listener)

            splitInstallManager.startInstall(request)
                // When the platform accepts your request to download
                // an on demand module, it binds it to the following session ID.
                // You use this ID to track further status updates for the request.
                .addOnSuccessListener { sessionId -> mySessionId = sessionId }
                .addOnFailureListener { exception ->
                    when ((exception as SplitInstallException).errorCode) {
                        // The request is rejected because there is at least one existing request
                        // that is currently downloading.
                        SplitInstallErrorCode.ACTIVE_SESSIONS_LIMIT_EXCEEDED -> {
                            handleError(moduleName, exception, "ACTIVE_SESSIONS_LIMIT_EXCEEDED")
                        }
                        // Google Play is unable to find the requested module based on
                        // the current installed version of the app, device, and user’s Google Play account.
                        SplitInstallErrorCode.MODULE_UNAVAILABLE -> {
                            handleError(moduleName, exception, "MODULE_UNAVAILABLE")
                        }
                        // Google Play received the request, but the request is not valid.
                        SplitInstallErrorCode.INVALID_REQUEST -> {
                            handleError(moduleName, exception, "INVALID_REQUEST")
                        }
                        // A session for a given session ID was not found.
                        SplitInstallErrorCode.SESSION_NOT_FOUND -> {
                            handleError(moduleName, exception, "SESSION_NOT_FOUND")
                        }
                        // The Play Core Library is not supported on the current device.
                        // That is, the device is not able to download and install features on demand.
                        SplitInstallErrorCode.API_NOT_AVAILABLE -> {
                            handleError(moduleName, exception, "API_NOT_AVAILABLE")
                        }
                        // The app is unable to register the request because of insufficient permissions.
                        // This typically occurs when the app is in the background.
                        // Attempt the request when the app returns to the foreground.
                        SplitInstallErrorCode.ACCESS_DENIED -> {
                            handleError(moduleName, exception, "ACCESS_DENIED")
                        }
                        // The request failed because of a network error.
                        SplitInstallErrorCode.NETWORK_ERROR -> {
                            handleError(moduleName, exception, "NETWORK_ERROR")
                        }
                        // The request contains one or more modules
                        // that have already been requested but have not yet been installed.
                        SplitInstallErrorCode.INCOMPATIBLE_WITH_EXISTING_SESSION -> {
                            handleError(moduleName, exception, "INCOMPATIBLE_WITH_EXISTING_SESSION")
                        }
                        // The service responsible for handling the request has died.
                        SplitInstallErrorCode.SERVICE_DIED -> {
                            handleError(moduleName, exception, "SERVICE_DIED")
                        }
                    }
                    moduleInstallListener.onFailure(exception)
                }
        }
    }

    private fun handleError(moduleName: String, exception: Exception, error: String) {
        logE(
            "SplitInstallHelper",
            "Split install [$moduleName] installation failed. $error",
            exception
        )
    }
}
