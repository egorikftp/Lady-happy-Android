package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery

import com.google.android.play.core.splitinstall.SplitInstallSessionState

sealed class DynamicFeatureEvent {
    data class NavigationEvent(val screen: DynamicScreen) : DynamicFeatureEvent()
    data class InstallConfirmationEvent(val status: SplitInstallSessionState) :
        DynamicFeatureEvent()

    data class Downloading(val progress: Int) : DynamicFeatureEvent()
    object Installing : DynamicFeatureEvent()
    object InstallErrorEvent : DynamicFeatureEvent()
}