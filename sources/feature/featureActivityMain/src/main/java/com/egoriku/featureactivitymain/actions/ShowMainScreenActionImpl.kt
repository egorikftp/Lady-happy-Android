package com.egoriku.featureactivitymain.actions

import android.content.Context
import com.egoriku.core.actions.ShowMainScreenAction
import com.egoriku.featureactivitymain.MainActivityTest
import org.jetbrains.anko.startActivity

class ShowMainScreenActionImpl : ShowMainScreenAction {
    override fun show(context: Context) {
        context.startActivity<MainActivityTest>()
    }
}