package com.egoriku.ladyhappy.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egoriku.ladyhappy.core.IFeatureProvider
import org.koin.android.ext.android.inject

class LaunchActivity : AppCompatActivity() {

    private val featureProvider: IFeatureProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(featureProvider.getMainActivityIntent(this))
        finish()
    }
}
