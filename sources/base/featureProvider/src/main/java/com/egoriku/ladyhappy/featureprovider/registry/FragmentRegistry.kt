package com.egoriku.ladyhappy.featureprovider.registry

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.featureprovider.AvailableFeatures

internal class FragmentRegistry : ClassRegistry() {

    companion object {

        fun findLandingFragment() = findFragment<Fragment>(AvailableFeatures.LANDING)

        fun findPhotoReportFragment() = findFragment<Fragment>(AvailableFeatures.PHOTO_REPORT)

        fun findSettingsFragment(): DialogFragment = findDialogFragment<DialogFragment>(AvailableFeatures.SETTINGS)
    }
}