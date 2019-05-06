package com.egoriku.ladyhappy.arch.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()

        super.onCreate(savedInstanceState)

        setContentView(provideLayout())
    }

    abstract fun injectDependencies()

    @LayoutRes
    abstract fun provideLayout(): Int
}