package com.egoriku.settings.action

import com.egoriku.core.actions.ISettingsFragmentAction
import com.egoriku.settings.presentation.SettingBottomSheetDialogFragment

class SettingsFragmentAction : ISettingsFragmentAction {

    override fun provideFragment() = SettingBottomSheetDialogFragment()
}