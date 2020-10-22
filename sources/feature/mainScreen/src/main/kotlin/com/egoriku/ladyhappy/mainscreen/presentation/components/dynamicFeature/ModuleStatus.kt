package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicFeature

import com.google.android.play.core.splitinstall.SplitInstallSessionState

sealed class ModuleStatus {
    data class Installing(val progress: Double) : ModuleStatus()
    object Unavailable : ModuleStatus()
    object Installed : ModuleStatus()
    class NeedsConfirmation(val state: SplitInstallSessionState) : ModuleStatus()
}