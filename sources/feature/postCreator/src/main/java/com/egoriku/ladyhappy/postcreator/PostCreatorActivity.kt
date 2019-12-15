package com.egoriku.ladyhappy.postcreator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat

class PostCreatorActivity : AppCompatActivity(R.layout.activity_post_creator) {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        SplitCompat.installActivity(this)
    }
}