package com.egoriku.featurelaunch.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egoriku.core.feature.IFeatureProvider
import org.koin.android.ext.android.inject

class LaunchActivity : AppCompatActivity() {

    private val featureProvider: IFeatureProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(featureProvider.getMainActivityIntent(this))
        finish()
    }
}
