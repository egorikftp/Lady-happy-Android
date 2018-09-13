package com.egoriku.photoreportfragment.action

import android.support.v4.app.Fragment
import com.egoriku.core.actions.IPhotoReportAction
import com.egoriku.photoreportfragment.presentation.PhotoReportFragment

class PhotoReportAction : IPhotoReportAction {

    override fun provideFragment(): Fragment = PhotoReportFragment.newInstance()
}