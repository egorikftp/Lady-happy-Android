package com.egoriku.giugi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import egoriku.com.guigiu.R

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
