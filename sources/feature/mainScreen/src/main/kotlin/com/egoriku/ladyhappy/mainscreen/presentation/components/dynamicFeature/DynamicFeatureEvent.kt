package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicFeature

import com.google.android.play.core.splitinstall.SplitInstallSessionState

sealed class DynamicFeatureEvent {
    data class ToastEvent(val message: String) : DynamicFeatureEvent()
    data class NavigationEvent(val fragmentClass: String) : DynamicFeatureEvent()
    data class InstallConfirmationEvent(val status: SplitInstallSessionState) : DynamicFeatureEvent()
    data class InstallErrorEvent(val status: SplitInstallSessionState) : DynamicFeatureEvent()
}