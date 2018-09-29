package com.egoriku.featureactivitymain.presentation.screen

import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.core.actions.IPhotoReportFragmentAction
import javax.inject.Inject

class ScreenFactory
@Inject constructor(
        private val landingFragmentAction: ILandingFragmentAction,
        private val photoReportFragmentAction: IPhotoReportFragmentAction) {

    fun getLanding(): ILandingFragmentAction = landingFragmentAction

    fun getPhotoReport(): IPhotoReportFragmentAction = photoReportFragmentAction
}