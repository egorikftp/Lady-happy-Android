package com.egoriku.featureactivitymain.actions

import android.content.Context
import com.egoriku.core.actions.IMainActivityAction
import com.egoriku.featureactivitymain.presentation.activity.MainActivity
import org.jetbrains.anko.startActivity

class MainActivityAction : IMainActivityAction {
    override fun show(context: Context) {
        context.startActivity<MainActivity>()
    }
}