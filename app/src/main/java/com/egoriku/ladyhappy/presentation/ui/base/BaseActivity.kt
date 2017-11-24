package com.egoriku.ladyhappy.presentation.ui.base

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    abstract fun getExtras(_intent: Intent)
    abstract fun closeActivity()
    abstract fun setUpToolbar()
    abstract fun getAttachedFragment(id: Int): Fragment
    abstract fun getAttachedFragment(tag: String): Fragment

    fun getContext(): Context {
        return this
    }
}