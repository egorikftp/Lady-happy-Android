package com.egoriku.ladyhappy.featureprovider.provider

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import com.egoriku.ladyhappy.featureprovider.registry.ActivityRegistry
import com.egoriku.ladyhappy.featureprovider.registry.FragmentRegistry

object FeatureScreen {

    fun getMainScreenIntent(context: Context): Intent = Intent(context, ActivityRegistry.findMainScreen())

    fun getLandingFragment() = FragmentRegistry.findLandingFragment()

    fun getPhotoReportFragment() = FragmentRegistry.findPhotoReportFragment()

    fun getSettingsFragment():DialogFragment = FragmentRegistry.findSettingsFragment()
}