package com.wildnet.weatherapplicationkotlin.app

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.wildnet.weatherapplicationkotlin.R

class App : MultiDexApplication() {

    private var persitanceEnabled: Boolean = false

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseInit()
        MultiDex.install(this)

        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id))
    }

    private fun FirebaseInit() {
        if (!persitanceEnabled) {
            if (!FirebaseApp.getApps(this).isEmpty()) {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true)
                persitanceEnabled = true
            }
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}