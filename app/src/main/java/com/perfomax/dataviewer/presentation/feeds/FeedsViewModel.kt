package com.perfomax.dataviewer.presentation.feeds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.feeds.LoadFeedUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase,
    private val loadFeedUseCase: LoadFeedUseCase,
): ViewModel(), FeedsContract {

    private val _uiState = MutableStateFlow(FeedsContract.State.initial())
    override val uiState: StateFlow<FeedsContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<FeedsContract.Effect?>(null)
    override val effect: StateFlow<FeedsContract.Effect?> = _effect.asStateFlow()

    init {
        _uiState.update { currentState->
            currentState.copy(
                feedUrl = "https://feeds-mic.s1.citilink.ru/yandex_offer/msk_cl.xml"
//                feedUrl = "https://api2.kiparo.com/static/it_news.xml"
            )
        }
    }

    override fun intent(event: FeedsContract.Event) {
        when(event) {
            is FeedsContract.Event.FeedUrlChangeEvent -> onFeedUrlChangeEvent(event.text)
            is FeedsContract.Event.SelectFeedElementEvent -> onSelectFeedElement(event.selectedFeedElement)
            is FeedsContract.Event.FeedNameEvent -> onFeedName(event.feedName)
            FeedsContract.Event.AddFeedClickEvent -> onAddFeedClick()
            FeedsContract.Event.OpenDialogSelectedFeedElementEvent -> openDialogSelectedFeedElement()
            FeedsContract.Event.CloseDialogSelectedFeedElementEvent -> closeDialogSelectedFeedElement()
            FeedsContract.Event.AddNewFeedEvent -> onAddNewFeed()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onFeedUrlChangeEvent(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedUrl = text,
                feedUrlError = text.isNotBlank()
            )
        }
    }

    private fun onAddFeedClick() {
        val feedUrl = _uiState.value.feedUrl
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    loadedFeed = loadFeedUseCase.execute(feedUrl)
                )
            }
        }
    }

    private fun openDialogSelectedFeedElement() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogSelectedFeedElement = true
            )
        }
    }

    private fun closeDialogSelectedFeedElement() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogSelectedFeedElement = false
            )
        }
    }

    private fun onSelectFeedElement(selectedElement: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedFeedElement = selectedElement
            )
        }
    }

    private fun onFeedName(feedName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedName = feedName
            )
        }
    }

    private fun onAddNewFeed() {
        val project = "MyProject"
        val feedName = _uiState.value.feedName
        val feedElement = _uiState.value.selectedFeedElement
        val feedElementCount = 0
        val feedUrl = _uiState.value.feedUrl
        val feedLoadTime = System.currentTimeMillis()
        val feedUpdateTime = "2024-03-21 20:52"
        Log.d("MyLog", "$project|$feedName|$feedElement|$feedElementCount|$feedUrl|$feedLoadTime|$feedUpdateTime")
        closeDialogSelectedFeedElement()
    }


}