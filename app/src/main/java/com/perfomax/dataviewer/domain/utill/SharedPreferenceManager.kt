package com.perfomax.dataviewer.domain.utill

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(
   private val sharedPreferences: SharedPreferences
) {

    //    https://www.youtube.com/watch?v=wkt59jo7Nh0&t=1470s
    // https://www.youtube.com/watch?v=zG4YgeSqjmQ
    // https://www.youtube.com/watch?v=vRNn9D_P2tc&t=819s
    // https://www.youtube.com/watch?v=McnVx7l5awk&t=417s
    // https://www.youtube.com/watch?v=MKcvEQOom3s
    // https://www.youtube.com/watch?v=tYZ2pGS95K4
//    https://www.youtube.com/watch?v=rpBYw5JMZHk

    // про сторажд:
    // -нехорошо делать сохранение данных в репозитории
    // -репозиторий это связующиее звено между доменном и датой
    // -логика хранения данных выносится в отдельный класс



//    //saving list in Shared Preference
//    fun setLists(list:ArrayList<String>){
//        val gson = Gson()
//        val json = gson.toJson(list)//converting list to Json
//        editor.putString("LIST",json)
//        editor.commit()
//    }
//    //getting the list from shared preference
//    fun getList():ArrayList<String>{
//        val gson = Gson()
//        val json = preferences.getString("LIST",null)
//        val type = object :TypeToken<ArrayList<String>>(){}.type//converting the json to list
//        return gson.fromJson(json,type)//returning the list
//    }


//    interface LocalPreferencesRepository {
//        fun getSearchHistoryItems(): List<String>
//        fun addSearchHistoryItem(item: String)
//    }
//
//    class LocalPreferencesRepositoryImpl(
//        private val sharedPreferences: SharedPreferences
//    ) : LocalPreferencesRepository {
//
//        override fun getSearchHistoryItems(): List<String> {
//            return Gson().fromJson(
//                sharedPreferences.getString(PREF_SEARCH_HISTORY, ""),
//                object : TypeToken<ArrayList<String>>() {}.type
//            ).orEmpty()
//        }
//
//        override fun addSearchHistoryItem(item: String) {
//            val listToSave = listOf(item).plus(getSearchHistoryItems())
//            val json = Gson().toJson(listToSave)
//            sharedPreferences.edit { putString(PREF_SEARCH_HISTORY, json) }
//        }
//
//        companion object {
//            private const val PREF_SEARCH_HISTORY = "PREF_SEARCH_HISTORY"
//        }
//    }




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