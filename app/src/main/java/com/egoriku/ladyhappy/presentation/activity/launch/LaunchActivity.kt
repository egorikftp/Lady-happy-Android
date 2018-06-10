package com.egoriku.ladyhappy.presentation.activity.launch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import org.jetbrains.anko.startActivity

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<MainActivity>()
        finish()
    }
}
