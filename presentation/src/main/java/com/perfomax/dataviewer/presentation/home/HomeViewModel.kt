package com.perfomax.dataviewer.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.feeds.GetAllFeedsUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


//sealed interface MenuUiState {
////    data object Loading : MenuUiState
//    object Loading : MenuUiState
//
//    @Immutable
//    data class Success(
//        val feedsList: ImmutableList<String>,
//        val selectedSectionIndex: Int = 0,
////        val product: ImmutableList<MenuItem>
//    ): MenuUiState {
//        fun selectionTitle(): String = feedsList[selectedSectionIndex]
//    }
//}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase,
): ViewModel(), HomeContract {

    private val _uiState = MutableStateFlow(HomeContract.State.initial())
    override val uiState: StateFlow<HomeContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<HomeContract.Effect?>(null)
    override val effect: StateFlow<HomeContract.Effect?> = _effect.asStateFlow()

    init {
        loadFeedsList()
    }

    override fun intent(event: HomeContract.Event) {
//        when(event) {
//            is HomeContract.Event.TextChangeEvent -> onTextFieldsChange(event.text)
//            HomeContract.Event.ClickEvent -> onClickTest()
//        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun loadFeedsList() {
        viewModelScope.launch {
            _uiState.update { currentState ->

                val selectedProject = getSelectedProjectUseCase.execute()
                Log.d("MyLog", "selectedProject: $selectedProject")
                val allFeeds = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
                Log.d("MyLog", "allFeeds: $allFeeds")

                currentState.copy(
                    feedsList = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
                )
            }
        }
    }


}