package com.perfomax.dataviewer.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.feeds.CountFeedElementsUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.GetAllFeedsUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase,
    private val countFeedElementsUseCase: CountFeedElementsUseCase
): ViewModel(), HomeContract {

    private val _uiState = MutableStateFlow(HomeContract.State.initial())
    override val uiState: StateFlow<HomeContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<HomeContract.Effect?>(null)
    override val effect: StateFlow<HomeContract.Effect?> = _effect.asStateFlow()


    override fun intent(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.ClickFeedNameEvent -> openDialogHomeScreenFeed(event.feedName)
            is HomeContract.Event.ClickFindFeedElement -> onFindFeedElementChange(event.findFeedElement)
            is HomeContract.Event.ChangeFeedEvent -> {}
            HomeContract.Event.CountFeedElementEvent -> countFeedElements()
            HomeContract.Event.UpdateFeedsListEvent -> loadFeedsList()
            HomeContract.Event.FindSelectedElementEvent -> onFindFeedElement()

            HomeContract.Event.ClickUpdateFeedEvent -> onUpdateSelectedFeed()
            HomeContract.Event.CloseDialogClickEvent -> closeDialogHomeScreenFeed()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun loadFeedsList() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    feedsList = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
                )
            }
        }
    }

    private fun countFeedElements() {
        viewModelScope.launch {
            _uiState.update { currentState -> currentState.copy(isUpdatingFeedList = true) }
            countFeedElementsUseCase.execute(_uiState.value.feedsList)
            _uiState.update { currentState -> currentState.copy(isUpdatingFeedList = false) }
            loadFeedsList()
        }
    }

    private fun openDialogHomeScreenFeed(feedName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogHomeScreenFeed = true,
                selectedFeedName = feedName
            )
        }
    }

    private fun closeDialogHomeScreenFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogHomeScreenFeed = false
            )
        }
        onClearFindFeedElementState()
    }

    private fun onClearFindFeedElementState(){
        _uiState.update { currentState ->
            currentState.copy(
                findFeedElement = ""
            )
        }
    }

    private fun onFindFeedElementChange(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                findFeedElement = text
            )
        }
    }

    private fun onFindFeedElement() {
//        Log.d("MyLog", "Выбранный фид для поиска: ${_uiState.value.selectedFeedName}")
//        Log.d("MyLog", "Элемент поиска в фиде: ${_uiState.value.findFeedElement}")
    }

    private fun onUpdateSelectedFeed() {
        Log.d("MyLog", "Обновление выбранного фида")
        _uiState.update { currentState ->
            currentState.copy(
            )
        }
    }


}