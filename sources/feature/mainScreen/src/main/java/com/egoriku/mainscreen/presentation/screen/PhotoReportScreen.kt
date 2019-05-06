package com.egoriku.mainscreen.presentation.screen

import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.featureprovider.provider.FeatureScreen
import com.egoriku.mainscreen.R
import com.egoriku.mainscreen.common.Constants.Tracking
import com.egoriku.mainscreen.presentation.screen.base.AppScreen

class PhotoReportScreen : AppScreen() {

    override val pageTitle: Int
        get() = R.string.navigation_view_photo_report_header

    override val trackingKey: String
        get() = Tracking.TRACKING_FRAGMENT_PHOTO_REPORT

    override fun getFragment(): Fragment = FeatureScreen.getPhotoReportFragment()
}
