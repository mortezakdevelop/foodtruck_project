package com.texonapp.foodtruck.util

import android.content.Context
import android.content.SharedPreferences
import com.texonapp.foodtruck.util.MainApplication.Companion.applicationContext

class SharedPreferenceUtil(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(SHARE_FILE_NAME, Context.MODE_PRIVATE)

    fun saveValue(key: String?, value: Any?) {
        val editor = preferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            else -> error("Wrong value")
        }
        editor.apply()
    }

    fun deleteValue() {
        val editor = preferences.edit()
        editor.clear()
        editor.commit()
    }

    fun getStringValue(key: String?): String? {
        return preferences.getString(key, "")
    }

    fun getBooleanValue(key: String?): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun cleanAll() {
        preferences.edit().clear().apply()
    }
}