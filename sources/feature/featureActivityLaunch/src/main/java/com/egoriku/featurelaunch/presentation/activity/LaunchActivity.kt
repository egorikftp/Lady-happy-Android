package com.egoriku.featurelaunch.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.egoriku.core.IApplication
import com.egoriku.core.actions.MainActivityAction
import com.egoriku.featurelaunch.di.LaunchActivityComponent
import javax.inject.Inject

class LaunchActivity : AppCompatActivity() {

    @Inject
    lateinit var showMainActivityAction: MainActivityAction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        showMainActivityAction.show(this)
        finish()
    }

    private fun inject() {
        LaunchActivityComponent.Initializer
                .init((applicationContext as IApplication).getAppComponent())
                .inject(this@LaunchActivity)
    }
}
