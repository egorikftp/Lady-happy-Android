package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.sharedmodel.params.EditParams
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import com.egoriku.ladyhappy.core.splitinstall.ISplitInstallHelper
import com.egoriku.ladyhappy.core.splitinstall.ModuleInstallListener
import com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.feature.EditDynamicFeatureFacade
import com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.feature.PostCreatorDynamicFeatureFacade
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DynamicFeatureViewModel(private val splitInstallHelper: ISplitInstallHelper) : ViewModel() {

    private val _events = MutableSharedFlow<DynamicFeatureEvent>()
    val events: SharedFlow<DynamicFeatureEvent> = _events.asSharedFlow()

    fun invokePostCreator(postCreatorParams: PostCreatorParams) {
        PostCreatorDynamicFeatureFacade(splitInstallHelper).installModule(
                object : ModuleInstallListener {
                    override fun onSuccess() {
                        viewModelScope.launch {
                            _events.emit(
                                    DynamicFeatureEvent.NavigationEvent(
                                            screen = DynamicScreen.PostCreator(params = postCreatorParams)
                                    )
                            )
                        }
                    }

                    override fun onFailure(exception: Exception?) {
                        viewModelScope.launch {
                            _events.emit(DynamicFeatureEvent.InstallErrorEvent)
                        }
                    }

                    override fun onRequiresUserConfirmation(sessionState: SplitInstallSessionState) {
                        viewModelScope.launch {
                            _events.emit(DynamicFeatureEvent.InstallConfirmationEvent(sessionState))
                        }
                    }

                    override fun onDownloadInProgress(progress: Long, totalBytes: Long) {
                        viewModelScope.launch {
                            DynamicFeatureEvent.Downloading(progress = (progress / totalBytes).toInt())
                        }
                    }

                    override fun onInstalling() {
                        viewModelScope.launch {
                            DynamicFeatureEvent.Installing
                        }
                    }
                }
        )
    }

    fun invokeEdit(editParams: EditParams) {
        EditDynamicFeatureFacade(splitInstallHelper).installModule(
                object : ModuleInstallListener {
                    override fun onSuccess() {
                        viewModelScope.launch {
                            _events.emit(
                                    DynamicFeatureEvent.NavigationEvent(
                                            screen = DynamicScreen.Edit(editParams = editParams)
                                    )
                            )
                        }
                    }

                    override fun onRequiresUserConfirmation(sessionState: SplitInstallSessionState) {
                        viewModelScope.launch {
                            _events.emit(DynamicFeatureEvent.InstallConfirmationEvent(sessionState))
                        }
                    }

                    override fun onFailure(exception: Exception?) {
                        viewModelScope.launch {
                            _events.emit(DynamicFeatureEvent.InstallErrorEvent)
                        }
                    }

                    override fun onDownloadInProgress(progress: Long, totalBytes: Long) {
                        viewModelScope.launch {
                            DynamicFeatureEvent.Downloading(progress = (progress / totalBytes).toInt())
                        }
                    }

                    override fun onInstalling() {
                        viewModelScope.launch {
                            DynamicFeatureEvent.Installing
                        }
                    }
                }
        )
    }
}