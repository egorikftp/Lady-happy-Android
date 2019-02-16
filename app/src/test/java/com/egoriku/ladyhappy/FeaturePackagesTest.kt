package com.egoriku.ladyhappy

import com.egoriku.ladyhappy.featureprovider.AvailableFeatures
import com.egoriku.landing.presentation.LandingPageFragment
import com.egoriku.mainscreen.presentation.activity.MainActivity
import com.egoriku.photoreport.presentation.PhotoReportFragment
import com.egoriku.settings.presentation.SettingBottomSheetDialogFragment
import org.junit.Assert.assertEquals
import org.junit.Test

class FeaturePackagesTest {

    @Test
    fun `feature packages test`() {
        assertEquals(AvailableFeatures.MAIN_SCREEN, MainActivity::class.java.name)
        assertEquals(AvailableFeatures.LANDING, LandingPageFragment::class.java.name)
        assertEquals(AvailableFeatures.PHOTO_REPORT, PhotoReportFragment::class.java.name)
        assertEquals(AvailableFeatures.SETTINGS, SettingBottomSheetDialogFragment::class.java.name)
    }
}