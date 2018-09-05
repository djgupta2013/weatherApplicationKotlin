package com.wildnet.weatherapplicationkotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wildnet.weatherapplicationkotlin.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar!!.hide()
    }
}
