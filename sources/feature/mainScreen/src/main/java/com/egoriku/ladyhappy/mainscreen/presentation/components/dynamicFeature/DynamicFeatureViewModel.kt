package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.constant.DYNAMIC_FEATURE_POST_CREATOR
import com.egoriku.extensions.logD
import com.google.android.play.core.ktx.*
import com.google.android.play.core.splitinstall.SplitInstallException
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DynamicFeatureViewModel(
        private val splitInstallManager: SplitInstallManager
) : ViewModel() {

    val postCreatorModuleStatus = getStatusLiveDataForModule(DYNAMIC_FEATURE_POST_CREATOR)

    private val _events: BroadcastChannel<DynamicFeatureEvent> = BroadcastChannel(Channel.BUFFERED)
    val events: Flow<DynamicFeatureEvent> = _events.asFlow()

    private fun getStatusLiveDataForModule(moduleName: String): LiveData<ModuleStatus> {
        return splitInstallManager.requestProgressFlow()
                .filter { state ->
                    state.moduleNames.contains(moduleName)
                }
                .map { state ->
                    logD("STATE $state")

                    when (state.status) {
                        SplitInstallSessionStatus.CANCELED -> ModuleStatus.Unavailable
                        SplitInstallSessionStatus.CANCELING -> ModuleStatus.Installing(0.0)
                        SplitInstallSessionStatus.DOWNLOADING -> ModuleStatus.Installing(
                                progress = state.bytesDownloaded.toDouble() / state.totalBytesToDownload
                        )
                        SplitInstallSessionStatus.DOWNLOADED -> ModuleStatus.Installed
                        SplitInstallSessionStatus.FAILED -> {
                            _events.send(DynamicFeatureEvent.InstallErrorEvent(state))
                            ModuleStatus.Unavailable
                        }
                        SplitInstallSessionStatus.INSTALLED -> ModuleStatus.Installed
                        SplitInstallSessionStatus.INSTALLING -> ModuleStatus.Installing(1.0)
                        SplitInstallSessionStatus.PENDING -> ModuleStatus.Installing(0.0)
                        SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> ModuleStatus.NeedsConfirmation(
                                state
                        )
                        SplitInstallSessionStatus.UNKNOWN -> ModuleStatus.Unavailable
                        else -> ModuleStatus.Unavailable
                    }
                }.catch {
                    _events.send(DynamicFeatureEvent.ToastEvent(
                            "Something went wrong. No install progress will be reported."
                    ))
                    emit(ModuleStatus.Unavailable)
                }.asLiveData()
    }

    fun invokePostCreator() {
        tryToOpenDynamicFeature(
                moduleName = DYNAMIC_FEATURE_POST_CREATOR,
                fragmentName = "com.egoriku.ladyhappy.postcreator.presentation.fragment.PostCreatorFragment"
        )
    }

    private fun tryToOpenDynamicFeature(moduleName: String, fragmentName: String) {
        if (splitInstallManager.installedModules.contains(moduleName)) {
            viewModelScope.launch {
                _events.send(DynamicFeatureEvent.NavigationEvent(fragmentName))
            }
        } else {
            val status = when (moduleName) {
                DYNAMIC_FEATURE_POST_CREATOR -> postCreatorModuleStatus.value
                else -> throw IllegalArgumentException("State not implemented")
            }
            if (status is ModuleStatus.NeedsConfirmation) {
                viewModelScope.launch {
                    _events.send(DynamicFeatureEvent.InstallConfirmationEvent(status.state))
                }
            } else {
                requestModuleInstallation(moduleName)
            }
        }
    }

    private fun requestModuleInstallation(moduleName: String) {
        viewModelScope.launch {
            try {
                splitInstallManager.requestInstall(listOf(moduleName))
            } catch (e: SplitInstallException) {
                _events.send(DynamicFeatureEvent.ToastEvent("Failed starting installation of $moduleName"))
            }
        }
    }
}