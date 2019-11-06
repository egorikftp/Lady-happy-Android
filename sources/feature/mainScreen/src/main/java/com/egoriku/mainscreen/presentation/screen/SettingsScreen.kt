package com.egoriku.mainscreen.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.navigation.screen.DialogFragmentScreen
import com.egoriku.mainscreen.common.Constants.Tracking.TRACKING_FRAGMENT_SETTINGS
import com.egoriku.mainscreen.common.TRACKING_KEY

class SettingsScreen(featureProvider: IFeatureProvider) : DialogFragmentScreen() {

    override val arguments: Bundle = bundleOf(
            TRACKING_KEY to TRACKING_FRAGMENT_SETTINGS
    )

    override val dialogFragment: DialogFragment = featureProvider.settingsDialogFragment

    override val tag: String = "SettingsDialog"
}