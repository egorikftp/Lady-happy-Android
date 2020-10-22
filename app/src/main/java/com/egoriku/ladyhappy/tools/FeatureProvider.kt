package com.egoriku.ladyhappy.tools

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.catalog.categories.presentation.fragment.CategoriesFragment
import com.egoriku.ladyhappy.login.presentation.LoginFragment
import com.egoriku.ladyhappy.settings.presentation.SettingFragment
import com.egoriku.ladyhappy.landing.presentation.LandingPageFragment
import com.egoriku.ladyhappy.mainscreen.presentation.MainActivity
import com.egoriku.photoreport.presentation.PhotoReportFragment
import com.egoriku.usedLibraries.presentation.UsedLibrariesFragment

internal class FeatureProvider : IFeatureProvider {

    override fun getMainActivityIntent(context: Context) = Intent(context, MainActivity::class.java)

    override val catalogFragment: Fragment
        get() = CategoriesFragment()

    override val landingFragment: Fragment
        get() = LandingPageFragment()

    override val loginFragment: Fragment
        get() = LoginFragment()

    override val photoReportFragment: Fragment
        get() = PhotoReportFragment()

    override val settingsFragment: Fragment
        get() = SettingFragment()

    override val usedLibrariesFragment: Fragment
        get() = UsedLibrariesFragment()
}