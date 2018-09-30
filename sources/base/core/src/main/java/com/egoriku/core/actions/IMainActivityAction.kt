package com.egoriku.core.actions

import android.content.Context
import android.content.Intent

interface IMainActivityAction {

    fun getIntent(context: Context): Intent
}