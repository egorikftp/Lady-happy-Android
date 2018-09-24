package com.egoriku.photoreportfragment.action

import android.support.v4.app.Fragment
import com.egoriku.core.actions.IPhotoReportFragmentAction
import com.egoriku.photoreportfragment.presentation.PhotoReportFragment

class PhotoReportFragmentAction : IPhotoReportFragmentAction {

    override fun provideFragment(): Fragment = PhotoReportFragment.newInstance()
}