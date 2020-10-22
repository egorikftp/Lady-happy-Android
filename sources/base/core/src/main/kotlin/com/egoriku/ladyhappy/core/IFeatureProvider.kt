package com.egoriku.ladyhappy.core

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

interface IFeatureProvider {

    fun getMainActivityIntent(context: Context): Intent

    val catalogFragment: Fragment

    val landingFragment: Fragment

    val loginFragment: Fragment

    val photoReportFragment: Fragment

    val settingsFragment: Fragment

    fun searchFragment(searchQuery: String): Fragment

    val usedLibrariesFragment: Fragment
}