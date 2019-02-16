package com.egoriku.settings.presentation

import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.ladyhappy.arch.pvm.BasePresenter
import javax.inject.Inject

internal class SettingsPagePresenter
@Inject constructor(private val analytics: IAnalytics)
    : BasePresenter<SettingsPageContract.View>(), SettingsPageContract.Presenter {


    override fun loadLandingData() {

    }

}