package com.perfomax.dataviewer.persentation.feeds

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.feeds.GetAllFeedsUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.LoadFeedUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.RemoveFeedUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.SaveFeedUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import com.perfomax.dataviewer.domain.usecases.projects.RemoveProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase,
    private val saveFeedUseCase: SaveFeedUseCase,
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val loadFeedUseCase: LoadFeedUseCase,
    private val removeFeedUseCase: RemoveFeedUseCase,
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
        viewModelScope.launch {
            loadFeedsList()
        }
    }

    override fun intent(event: FeedsContract.Event) {
        when(event) {
            is FeedsContract.Event.FeedUrlChangeEvent -> onFeedUrlChangeEvent(event.text)
            is FeedsContract.Event.SelectFeedElementEvent -> onSelectFeedElement(event.selectedFeedElement)
            is FeedsContract.Event.FeedNameEvent -> onFeedName(event.feedName)
            is FeedsContract.Event.SelectRemovedFeedEvent -> onSelectRemovedFeed(event.removedFeed)
            FeedsContract.Event.AddFeedClickEvent -> onAddFeedClick()
            FeedsContract.Event.OpenDialogSelectedFeedElementEvent -> openDialogSelectedFeedElement()
            FeedsContract.Event.CloseDialogSelectedFeedElementEvent -> closeDialogSelectedFeedElement()
            FeedsContract.Event.AddNewFeedEvent -> onAddNewFeed()
            FeedsContract.Event.CloseDialogRemoveEvent -> closeDialogRemoveFeed()
            FeedsContract.Event.RemoveFeedClickEvent -> onRemoveFeed()
            FeedsContract.Event.UpdateProjectEvent -> updateCart()
            FeedsContract.Event.SwitchScreenToFeedsListEvent -> onSwitchToFeedsList()

        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onClearUiFieldsState(){
        _uiState.update { currentState ->
            currentState.copy(
                feedName = "",
                selectedFeedElement = ""
            )
        }
    }

    private suspend fun loadFeedsList() {
        _uiState.update { currentState ->
            currentState.copy(
                feedsList = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
            )
        }
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
        onSwitchToFeedElements()
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

    private fun onSelectRemovedFeed(removeFeedName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                removedFeed = removeFeedName
            )
        }
        openDialogRemoveFeed()
    }

    private fun onRemoveFeed() {
        viewModelScope.launch {
            removeFeedUseCase.execute(_uiState.value.removedFeed)
            loadFeedsList()
        }
        closeDialogRemoveFeed()
    }

    private fun openDialogRemoveFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogRemoveFeed = true,
            )
        }
    }

    private fun closeDialogRemoveFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogRemoveFeed = false,
            )
        }
    }

    private fun onSwitchToFeedsList(){
        _uiState.update { currentState ->
            currentState.copy(
                isFeedsList = true
            )
        }
    }

    private fun onSwitchToFeedElements(){
        _uiState.update { currentState ->
            currentState.copy(
                isFeedsList = false
            )
        }
    }


    fun updateCart(){
        viewModelScope.launch {
            loadFeedsList()
        }
    }

    private fun onAddNewFeed() {
        viewModelScope.launch {
            val project = getSelectedProjectUseCase.execute()
            val feedName = _uiState.value.feedName.trim()
            val feedElement = _uiState.value.selectedFeedElement
            val feedElementCount = 0
            val feedUrl = _uiState.value.feedUrl
            val feedUpdateTime = "2024-03-21 20:52"

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val instant = Instant.ofEpochMilli(System.currentTimeMillis())
            val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            val feedLoadTime = formatter.format(date)

            val feed = "projectName:$project;" +
                       "feedName:$feedName;" +
                       "feedElement:$feedElement;" +
                       "feedElementCount:$feedElementCount;" +
                       "feedUrl:$feedUrl;" +
                       "feedUpdateTime:$feedUpdateTime;" +
                       "feedLoadTime:$feedLoadTime"
            saveFeedUseCase.execute(feed)
            onClearUiFieldsState()
            closeDialogSelectedFeedElement()
            loadFeedsList()
        }
        onSwitchToFeedsList()
    }

}