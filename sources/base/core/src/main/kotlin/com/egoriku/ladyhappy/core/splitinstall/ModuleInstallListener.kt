package com.egoriku.ladyhappy.core.splitinstall

import androidx.annotation.UiThread
import com.google.android.play.core.splitinstall.SplitInstallSessionState

@UiThread
interface ModuleInstallListener {

    fun onSuccess()

    fun onFailure(exception: Exception? = null)

    fun onRequiresUserConfirmation(sessionState: SplitInstallSessionState)

    fun onDownloadInProgress(progress: Long, totalBytes: Long)

    fun onInstalling()
}