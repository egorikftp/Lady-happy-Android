package com.egoriku.core.actions

import android.content.Context

interface MainActivityAction {

    @Deprecated("Need return Intent")
    fun show(context: Context)
}