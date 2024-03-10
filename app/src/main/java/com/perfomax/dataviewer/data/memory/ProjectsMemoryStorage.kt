package com.perfomax.dataviewer.data.memory

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.getSystemService
import com.perfomax.dataviewer.data.storage.ProjectsStorage

class ProjectsMemoryStorage: ProjectsStorage {


//    val storageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager
//    val storageVolumes = storageManager.storageVolumes

//    // Shared pref mode
//    val PRIVATE_MODE = 0
//
//    // SharedPref FileName
//    private val PREF_FILE_NAME = "SharedPreferencesAuth" // Название файла с пользователями и паролями
//
//    val pref: SharedPreferences? = context?.getSharedPreferences(PREF_FILE_NAME, PRIVATE_MODE)
//    private val editor: SharedPreferences.Editor? = pref?.edit()

    override fun getAll(): List<String> {
        return listOf("1","2")
    }

    override fun add(projectName: String) {

    }

    override fun remove(id: String) {

    }
}