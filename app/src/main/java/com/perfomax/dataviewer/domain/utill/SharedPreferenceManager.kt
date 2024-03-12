package com.perfomax.dataviewer.domain.utill

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(
   private val sharedPreferences: SharedPreferences
) {
    fun sava(keyName: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(keyName, text)
        editor.apply()
    }

    fun sava(keyName: String, text: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(keyName, text)
        editor.apply()
    }

    fun sava(keyName: String, text: Float) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putFloat(keyName, text)
        editor.apply()
    }

    fun sava(keyName: String, text: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(keyName, text)
        editor.apply()
    }

    fun getValueInt(keyName: String): Int {
        return sharedPreferences.getInt(keyName, 0)
    }

    fun getValueBoolean(keyName: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(keyName, defaultValue)
    }

    fun getValueString(keyName: String): String? {
        return sharedPreferences.getString(keyName, "")
    }

    fun getValueFloat(keyName: String): Float {
        return sharedPreferences.getFloat(keyName, 0.0f)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun removeValue(keyName: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(keyName)
        editor.apply()
    }

}