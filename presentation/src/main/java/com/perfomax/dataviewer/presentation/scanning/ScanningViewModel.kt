package com.perfomax.dataviewer.presentation.scanning

import android.app.Application
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.feeds.LoadFeedUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.SearchFeedElementUseCase
import com.perfomax.dataviewer.ui.utils.isConnected
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ScanningViewModel.ScanningViewModelFactory::class)
class ScanningViewModel @AssistedInject constructor(
    @Assisted val feedUrl: String,
    private val context: Application,
    private val loadFeedUseCase: LoadFeedUseCase,
    private val searchFeedElementUseCase: SearchFeedElementUseCase
): ViewModel(), ScanningContract {

    private val _uiState = MutableStateFlow(ScanningContract.State.initial())
    override val uiState: StateFlow<ScanningContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<ScanningContract.Effect?>(null)
    override val effect: StateFlow<ScanningContract.Effect?> = _effect.asStateFlow()

    init {
        _uiState.update { currentState->
            currentState.copy(feedUrl = feedUrl)
        }
    }

    override fun intent(event: ScanningContract.Event) {
        when(event) {
            is ScanningContract.Event.FeedUrlChangeEvent -> { onFeedUrlChangeEvent(event.feedUrl) }
            is ScanningContract.Event.SearchFeedElementChangeEvent -> { onSearchFeedValueChangeEvent(event.feedSearchElement) }
            ScanningContract.Event.LoadingFeedClickEvent -> onLoadFeed()
            ScanningContract.Event.SearchFeedElementClickEvent -> onSearchFeedValueEvent()
            ScanningContract.Event.CloseDialogIsConnectedEvent -> closeDialogIsConnected()
            ScanningContract.Event.CloseDialogIsNotFindEvent -> closeDialogIsNotFind()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onFeedUrlChangeEvent(feedUrl: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedUrl = feedUrl,
                feedUrlError = feedUrl.isNotBlank()
            )
        }
    }

    private fun onLoadFeed() {
        if (context.isConnected()) {
            viewModelScope.launch {
                _uiState.update { currentState -> currentState.copy(isScanningFeed = true) }
                _uiState.update {  currentState ->
                    currentState.copy(
                        isFeedScanningResponse = true,
                        loadedFeed = loadFeedUseCase.execute(feedUrl = _uiState.value.feedUrl)
                    )
                }
                _uiState.update { currentState -> currentState.copy(isScanningFeed = false) }
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    openDialogIsConnected = true
                )
            }
        }

    }

    private fun onSearchFeedValueChangeEvent(feedSearchValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedSearchValue = feedSearchValue,
                feedSearchValueError = feedSearchValue.isNotBlank()
            )
        }
    }

    private fun onSearchFeedValueEvent() {
        viewModelScope.launch {
            val loadedFeed = searchFeedElementUseCase.execute(searchedFeedElement = _uiState.value.feedSearchValue)
            val feedSearchValueIndex = loadedFeed.indexOf(loadedFeed.find { it.contains(_uiState.value.feedSearchValue) })
            if (feedSearchValueIndex != -1) {
                _uiState.update { currentState ->
                    currentState.copy(
                        loadedFeed = loadedFeed,
                        listState = LazyListState(feedSearchValueIndex)
                    )
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        openDialogIsNotFind = true
                    )
                }
            }

        }
    }

    private fun closeDialogIsConnected() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogIsConnected = false
            )
        }
    }

    private fun closeDialogIsNotFind() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogIsNotFind = false
            )
        }
    }

    @AssistedFactory
    interface ScanningViewModelFactory {
        fun create(feedUrl: String): ScanningViewModel
    }

}