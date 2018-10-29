package com.egoriku.settings.presentation

import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.ui.arch.pvm.BasePresenter
import javax.inject.Inject

internal class SettingsPagePresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper)
    : BasePresenter<SettingsPageContract.View>(), SettingsPageContract.Presenter {


    override fun loadLandingData() {

    }

}