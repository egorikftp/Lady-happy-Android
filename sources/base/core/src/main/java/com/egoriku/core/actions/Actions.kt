package com.egoriku.core.actions

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

interface BaseActivityAction {
    fun getIntent(context: Context): Intent
}

interface BaseFragmentAction {
    fun provideFragment(): Fragment
}

interface BaseDialogFragmentAction {
    fun provideFragment(): DialogFragment
}

interface IMainActivityAction : BaseActivityAction

interface ILandingFragmentAction : BaseFragmentAction
interface IPhotoReportFragmentAction : BaseFragmentAction

interface ISettingsFragmentAction : BaseDialogFragmentAction