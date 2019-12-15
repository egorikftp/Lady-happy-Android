package com.egoriku.ladyhappy.tools

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.catalog.root.presentation.fragment.RootCatalogFragment
import com.egoriku.ladyhappy.landing.presentation.LandingPageFragment
import com.egoriku.mainscreen.presentation.activity.MainActivity
import com.egoriku.photoreport.presentation.PhotoReportFragment
import com.egoriku.settings.presentation.SettingBottomSheetDialogFragment

class FeatureProvider : IFeatureProvider {

    override fun getMainActivityIntent(context: Context) = Intent(context, MainActivity::class.java)

    override val catalogFragment: Fragment
        get() = RootCatalogFragment()

    override val landingFragment: Fragment
        get() = LandingPageFragment()

    override val photoReportFragment: Fragment
        get() = PhotoReportFragment()

    override val settingsDialogFragment: DialogFragment
        get() = SettingBottomSheetDialogFragment()
}