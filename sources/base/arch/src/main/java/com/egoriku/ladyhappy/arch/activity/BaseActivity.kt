package com.egoriku.ladyhappy.arch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()

        super.onCreate(savedInstanceState)
    }

    open fun injectDependencies() = Unit
}