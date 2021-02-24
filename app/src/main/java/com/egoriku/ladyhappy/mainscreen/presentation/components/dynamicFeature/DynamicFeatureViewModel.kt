package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.constant.DYNAMIC_FEATURE_POST_CREATOR
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import com.egoriku.ladyhappy.extensions.logD
import com.google.android.play.core.ktx.*
import com.google.android.play.core.splitinstall.SplitInstallException
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DynamicFeatureViewModel(
        private val splitInstallManager: SplitInstallManager
) : ViewModel() {

    private val _events = MutableSharedFlow<DynamicFeatureEvent>()
    val events: SharedFlow<DynamicFeatureEvent> = _events

    val postCreatorModuleStatus: StateFlow<ModuleStatus> = getStatusFlowForModule(DYNAMIC_FEATURE_POST_CREATOR)

    private fun getStatusFlowForModule(moduleName: String): StateFlow<ModuleStatus> {
        return splitInstallManager.requestProgressFlow()
                .filter { state -> state.moduleNames.contains(moduleName) }
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
                            viewModelScope.launch {
                                _events.emit(DynamicFeatureEvent.InstallErrorEvent(state))
                            }
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
                    viewModelScope.launch {
                        _events.emit(DynamicFeatureEvent.ToastEvent(
                                "Something went wrong. No install progress will be reported."
                        ))
                    }
                    emit(ModuleStatus.Unavailable)
                }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ModuleStatus.None)
    }

    fun invokePostCreatorOrNoting(postCreatorParams: PostCreatorParams) {
        if (splitInstallManager.installedModules.contains(DYNAMIC_FEATURE_POST_CREATOR)) {
            viewModelScope.launch {
                _events.emit(
                        DynamicFeatureEvent.NavigationEvent(
                                screen = DynamicScreen.PostCreator(params = postCreatorParams)
                        )
                )
            }
        }
    }

    fun invokePostCreatorOrInstall() = tryToOpenDynamicFeature(
            moduleName = DYNAMIC_FEATURE_POST_CREATOR,
            dynamicScreen = DynamicScreen.PostCreator()
    )

    private fun tryToOpenDynamicFeature(moduleName: String, dynamicScreen: DynamicScreen) {
        if (splitInstallManager.installedModules.contains(moduleName)) {
            viewModelScope.launch {
                _events.emit(DynamicFeatureEvent.NavigationEvent(dynamicScreen))
            }
        } else {
            val status = when (moduleName) {
                DYNAMIC_FEATURE_POST_CREATOR -> postCreatorModuleStatus.value
                else -> throw IllegalArgumentException("State not implemented")
            }
            if (status is ModuleStatus.NeedsConfirmation) {
                viewModelScope.launch {
                    _events.emit(DynamicFeatureEvent.InstallConfirmationEvent(status.state))
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
                _events.emit(DynamicFeatureEvent.ToastEvent("Failed starting installation of $moduleName"))
            }
        }
    }
}