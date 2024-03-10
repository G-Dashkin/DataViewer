package com.perfomax.dataviewer.presentation.projects

import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.domain.repository.DatastoreRepository
import com.perfomax.dataviewer.presentation.home.HomeContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
//import javax.inject.Inject

class ProjectsViewModel (
    private val datastoreRepository : DatastoreRepository
): ViewModel(), ProjectsContract {

    private val _uiState = MutableStateFlow(ProjectsContract.State.initial())
    override val uiState: StateFlow<ProjectsContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<ProjectsContract.Effect?>(null)
    override val effect: StateFlow<ProjectsContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: ProjectsContract.Event) {
        when(event) {
            is ProjectsContract.Event.TextChangeEvent -> { }
            ProjectsContract.Event.ClickEvent -> { }
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    fun storeUserName(value:String) = runBlocking {
        datastoreRepository.putString("USER_NAME",value)
    }
    fun getUserName():String = runBlocking {
        datastoreRepository.getString("USER_NAME")!!
    }

    fun clearPreferences(key:String) = runBlocking {
        datastoreRepository.clearPreferences(key)
    }

//    private val dataStore = DataStoreManager()
//
//    val getTheme = dataStore.getTheme().asLiveData(Dispatchers.IO)
//
//    fun setTheme(isDarkMode : Boolean) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.setTheme(isDarkMode)
//        }
//    }
}