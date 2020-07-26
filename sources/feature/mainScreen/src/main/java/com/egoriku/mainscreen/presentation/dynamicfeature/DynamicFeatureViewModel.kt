package com.egoriku.mainscreen.presentation.dynamicfeature

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egoriku.extensions.Event
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class DynamicFeatureViewModel(
        application: Application
) : AndroidViewModel(application) {

    private val splitInstallManager = SplitInstallManagerFactory.create(getApplication())

    private val listener = initSplitInstallStateUpdatedListener()

    private val _installStatus = MutableLiveData<Event<Boolean>>()

    val installStatus: LiveData<Event<Boolean>> = _installStatus

    init {
        splitInstallManager.registerListener(listener)
    }

    fun installDynamicFeature(name: String) {
        if (splitInstallManager.installedModules.contains(name)) {
            _installStatus.value = Event(true)
            return
        }

        val request = SplitInstallRequest.newBuilder()
                .addModule(name)
                .build()

        splitInstallManager.startInstall(request)
    }

    override fun onCleared() {
        super.onCleared()
        splitInstallManager.unregisterListener(listener)
    }

    private fun initSplitInstallStateUpdatedListener(): SplitInstallStateUpdatedListener {
        return SplitInstallStateUpdatedListener { state ->
            when (state.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                }
                SplitInstallSessionStatus.INSTALLING -> {
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    _installStatus.value = Event(true)
                }
                SplitInstallSessionStatus.FAILED -> {
                }
                SplitInstallSessionStatus.DOWNLOADED -> {
                }
                SplitInstallSessionStatus.CANCELED -> {
                }
                SplitInstallSessionStatus.CANCELING -> {
                }
                SplitInstallSessionStatus.PENDING -> {
                }
                SplitInstallSessionStatus.UNKNOWN -> {
                }
            }
        }
    }
}