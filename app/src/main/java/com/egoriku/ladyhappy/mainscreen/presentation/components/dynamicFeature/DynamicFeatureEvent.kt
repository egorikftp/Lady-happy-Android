package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicFeature

import com.egoriku.ladyhappy.core.sharedmodel.params.EditParams
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import com.google.android.play.core.splitinstall.SplitInstallSessionState

sealed class DynamicFeatureEvent {
    data class ToastEvent(val message: String) : DynamicFeatureEvent()
    data class NavigationEvent(val screen: DynamicScreen) : DynamicFeatureEvent()
    data class InstallConfirmationEvent(val status: SplitInstallSessionState) : DynamicFeatureEvent()
    data class InstallErrorEvent(val status: SplitInstallSessionState) : DynamicFeatureEvent()
}

sealed class DynamicScreen {

    data class PostCreator(
            val className: String = "com.egoriku.ladyhappy.postcreator.presentation.PostCreatorFragment",
            val params: PostCreatorParams = PostCreatorParams()
    ) : DynamicScreen()

    data class Edit(
            val className: String = "com.egoriku.ladyhappy.edit.presentation.EditFragment",
            val editParams: EditParams
    ) : DynamicScreen()
}