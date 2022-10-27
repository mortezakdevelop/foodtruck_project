package com.texonapp.foodtruck.util

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

import com.texonapp.foodtruck.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        var sharedPreferenceUtil: SharedPreferenceUtil? = null
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferenceUtil = SharedPreferenceUtil(applicationContext)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        MapboxSearchSdk.initialize(
//            application = this,
//            accessToken = getString(R.string.mapbox_access_token),
//            locationEngine = LocationEngineProvider.getBestLocationEngine(this)
//        )
    }
}