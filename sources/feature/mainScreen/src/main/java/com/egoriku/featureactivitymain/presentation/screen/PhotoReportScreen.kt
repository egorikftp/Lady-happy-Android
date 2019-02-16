package com.egoriku.featureactivitymain.presentation.screen

import androidx.fragment.app.Fragment
import com.egoriku.core.actions.IPhotoReportFragmentAction
import ru.terrakok.cicerone.android.support.SupportAppScreen

class PhotoReportScreen(private val photoReportFragmentAction: IPhotoReportFragmentAction) : SupportAppScreen() {

    override fun getFragment(): Fragment = photoReportFragmentAction.provideFragment()
}
