package com.egoriku.core.actions

import android.content.Context

interface IMainActivityAction {

    @Deprecated("Need return Intent")
    fun show(context: Context)
}