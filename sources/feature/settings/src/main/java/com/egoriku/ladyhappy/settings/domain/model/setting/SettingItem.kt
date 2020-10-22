package com.egoriku.ladyhappy.settings.domain.model.setting

import androidx.annotation.StringRes

sealed class SettingItem(@StringRes open val stringResource: Int) {

    data class Header(override val stringResource: Int) : SettingItem(stringResource)
    data class NonClickable(val resource: String) : SettingItem(-1)

    data class Theme(override val stringResource: Int) : SettingItem(stringResource)
    data class UsedLibraries(override val stringResource: Int) : SettingItem(stringResource)
    data class Review(override val stringResource: Int) : SettingItem(stringResource)
}