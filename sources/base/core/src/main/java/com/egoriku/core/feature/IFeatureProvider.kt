package com.egoriku.core.feature

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

interface IFeatureProvider {

    fun getMainActivityIntent(context: Context): Intent

    val catalogFragment: Fragment

    val landingFragment: Fragment

    val photoReportFragment: Fragment

    val settingsDialogFragment: DialogFragment
}