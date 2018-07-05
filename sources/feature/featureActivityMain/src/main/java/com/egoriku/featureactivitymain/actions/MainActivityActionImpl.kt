package com.egoriku.featureactivitymain.actions

import android.content.Context
import com.egoriku.core.actions.MainActivityAction
import com.egoriku.featureactivitymain.presentation.activity.MainActivity
import org.jetbrains.anko.startActivity

class MainActivityActionImpl : MainActivityAction {
    override fun show(context: Context) {
        context.startActivity<MainActivity>()
    }
}