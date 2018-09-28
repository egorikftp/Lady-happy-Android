package com.egoriku.featureactivitymain.presentation.screen

import android.support.v4.app.Fragment
import com.egoriku.core.actions.IPhotoReportFragmentAction
import ru.terrakok.cicerone.android.support.SupportAppScreen

class PhotoReportScreen(private val photoReportFragmentAction: IPhotoReportFragmentAction) : SupportAppScreen() {

    override fun getFragment(): Fragment = photoReportFragmentAction.provideFragment()
}
