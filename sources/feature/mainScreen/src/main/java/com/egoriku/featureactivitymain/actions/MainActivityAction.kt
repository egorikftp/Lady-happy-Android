package com.egoriku.featureactivitymain.actions

import android.content.Context
import android.content.Intent
import com.egoriku.core.actions.IMainActivityAction
import com.egoriku.featureactivitymain.presentation.activity.MainActivity

class MainActivityAction : IMainActivityAction {

    override fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
}