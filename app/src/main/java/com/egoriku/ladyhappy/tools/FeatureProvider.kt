package com.egoriku.ladyhappy.tools

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.catalog.categories.presentation.fragment.CategoriesFragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.detailpage.presentation.DetailPageFragment
import com.egoriku.ladyhappy.landing.presentation.LandingPageFragment
import com.egoriku.ladyhappy.login.presentation.LoginFragment
import com.egoriku.ladyhappy.mainscreen.presentation.MainActivity
import com.egoriku.ladyhappy.mainscreen.presentation.search.SearchFragment
import com.egoriku.ladyhappy.photoreport.presentation.PhotoReportFragment
import com.egoriku.ladyhappy.settings.presentation.SettingFragment
import com.egoriku.ladyhappy.usedLibraries.presentation.UsedLibrariesFragment

internal class FeatureProvider : IFeatureProvider {

    override val catalogFragment: Fragment
        get() = CategoriesFragment()

    override val detailPage: Fragment
        get() = DetailPageFragment()

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

    override fun getMainActivityIntent(context: Context) = Intent(context, MainActivity::class.java)

    override fun getSearchFragment(searchQuery: String) = SearchFragment.newInstance(searchQuery)
}