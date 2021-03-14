package com.egoriku.ladyhappy.core

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

interface IFeatureProvider {

    val catalogFragment: Fragment
    val detailPage: Fragment
    val landingFragment: Fragment
    val loginFragment: Fragment
    val photoReportFragment: Fragment
    val settingsFragment: Fragment
    val usedLibrariesFragment: Fragment

    fun getMainActivityIntent(context: Context): Intent
    fun getSearchFragment(searchQuery: String): Fragment
}