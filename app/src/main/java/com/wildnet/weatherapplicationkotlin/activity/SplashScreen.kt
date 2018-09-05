package com.wildnet.weatherapplicationkotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wildnet.weatherapplicationkotlin.R
import android.content.Intent



class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 5 seconds
                    Thread.sleep((5 * 1000).toLong())

                    // After 5 seconds redirect to another intent
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)

                    //Remove activity
                    finish()
                } catch (e: Exception) {
                }

            }
        }
        // start thread
        background.start()

    }
}
