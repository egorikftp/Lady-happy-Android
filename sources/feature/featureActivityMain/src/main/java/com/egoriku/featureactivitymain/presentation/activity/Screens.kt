package com.egoriku.featureactivitymain.presentation.activity

import android.support.v4.app.Fragment
import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.core.actions.IPhotoReportFragmentAction
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class LandingScreen : SupportAppScreen() {
    @Inject
    lateinit var landingFragmentAction: ILandingFragmentAction

    override fun getFragment(): Fragment = landingFragmentAction.provideFragment()
}

class PhotoReportScreen : SupportAppScreen() {
    @Inject
    lateinit var photoReportFragmentAction: IPhotoReportFragmentAction

    override fun getFragment(): Fragment = photoReportFragmentAction.provideFragment()
}
