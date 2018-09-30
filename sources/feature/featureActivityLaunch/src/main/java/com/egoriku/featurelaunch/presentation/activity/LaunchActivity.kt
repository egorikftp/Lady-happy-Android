package com.egoriku.featurelaunch.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.egoriku.core.actions.IMainActivityAction
import com.egoriku.core.di.findDependencies
import com.egoriku.featurelaunch.di.LaunchActivityComponent
import javax.inject.Inject

class LaunchActivity : AppCompatActivity() {

    @Inject
    lateinit var showMainActivityAction: IMainActivityAction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        startActivity(showMainActivityAction.getIntent(this))
        finish()
    }

    private fun inject() {
        LaunchActivityComponent.Initializer.init(findDependencies())
                .inject(this@LaunchActivity)
    }
}
