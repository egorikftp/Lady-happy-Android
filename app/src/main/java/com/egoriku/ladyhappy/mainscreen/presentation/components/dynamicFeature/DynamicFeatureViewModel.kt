package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.sharedmodel.key.EDIT_DYNAMIC_FEATURE
import com.egoriku.ladyhappy.core.sharedmodel.key.POST_CREATOR_DYNAMIC_FEATURE
import com.egoriku.ladyhappy.core.sharedmodel.params.EditParams
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import com.egoriku.ladyhappy.extensions.logD
import com.google.android.play.core.ktx.*
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DynamicFeatureViewModel(
        private val splitInstallManager: SplitInstallManager
) : ViewModel() {

    private val _events = MutableSharedFlow<DynamicFeatureEvent>()
    val events: SharedFlow<DynamicFeatureEvent> = _events

    val postCreatorModuleStatus: StateFlow<ModuleStatus> = getStatusFlowForModule(POST_CREATOR_DYNAMIC_FEATURE)
    val editModuleStatus: StateFlow<ModuleStatus> = getStatusFlowForModule(EDIT_DYNAMIC_FEATURE)

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
        if (splitInstallManager.installedModules.contains(POST_CREATOR_DYNAMIC_FEATURE)) {
            viewModelScope.launch {
                _events.emit(
                        DynamicFeatureEvent.NavigationEvent(
                                screen = DynamicScreen.PostCreator(params = postCreatorParams)
                        )
                )
            }
        }
    }

    fun invokeEditOrInstall(editParams: EditParams) = tryToOpenDynamicFeature(
            moduleName = EDIT_DYNAMIC_FEATURE,
            dynamicScreen = DynamicScreen.Edit(editParams = editParams)
    )

    fun invokePostCreatorOrInstall() = tryToOpenDynamicFeature(
            moduleName = POST_CREATOR_DYNAMIC_FEATURE,
            dynamicScreen = DynamicScreen.PostCreator()
    )

    private fun tryToOpenDynamicFeature(moduleName: String, dynamicScreen: DynamicScreen) {
        if (splitInstallManager.installedModules.contains(moduleName)) {
            viewModelScope.launch {
                _events.emit(DynamicFeatureEvent.NavigationEvent(dynamicScreen))
            }
        } else {
            val status = when (moduleName) {
                POST_CREATOR_DYNAMIC_FEATURE -> postCreatorModuleStatus.value
                EDIT_DYNAMIC_FEATURE -> editModuleStatus.value
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
            runCatching {
                splitInstallManager.requestInstall(listOf(moduleName))
            }.getOrElse {
                _events.emit(DynamicFeatureEvent.ToastEvent("Failed starting installation of $moduleName"))
            }
        }
    }
}