package com.egoriku.featurelaunch.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egoriku.ladyhappy.featureprovider.provider.FeatureScreen

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(FeatureScreen.getMainScreenIntent(this))
        finish()
    }
}
