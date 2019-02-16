package com.egoriku.settings.action

import com.egoriku.core.actions.ISettingsFragmentAction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingsFragmentAction : ISettingsFragmentAction {

    override fun provideFragment() = BottomSheetDialogFragment()
}