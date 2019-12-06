package com.egoriku.ladyhappy.arch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(
        layoutId: Int
) : AppCompatActivity(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()

        super.onCreate(savedInstanceState)
    }

    open fun injectDependencies() = Unit
}