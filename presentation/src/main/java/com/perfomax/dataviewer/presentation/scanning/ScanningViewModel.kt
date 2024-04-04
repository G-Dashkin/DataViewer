package com.perfomax.dataviewer.presentation.scanning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.feeds.LoadFeedUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.SearchFeedElementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanningViewModel @Inject constructor(
    private val loadFeedUseCase: LoadFeedUseCase,
    private val searchFeedElementUseCase: SearchFeedElementUseCase
): ViewModel(), ScanningContract {

    private val _uiState = MutableStateFlow(ScanningContract.State.initial())
    override val uiState: StateFlow<ScanningContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<ScanningContract.Effect?>(null)
    override val effect: StateFlow<ScanningContract.Effect?> = _effect.asStateFlow()

    init {
        _uiState.update { currentState->
            currentState.copy(
//                feedUrl = "https://citilink.ru"
//                feedUrl = "citilink.в"
                feedUrl = "https://feeds-mic.s1.citilink.ru/yandex_offer/spb_cl.xml"
//                feedUrl = "https://feeds-mic.s1.citilink.ru/yandex_offer/spb_cl.xml"
//                feedUrl = "https://api2.kiparo.com/static/it_news.xml"
            )
        }
    }

    override fun intent(event: ScanningContract.Event) {
        when(event) {
            is ScanningContract.Event.FeedUrlChangeEvent -> { onFeedUrlChangeEvent(event.feedUrl) }
            is ScanningContract.Event.SearchFeedElementChangeEvent -> { onSearchFeedValueChangeEvent(event.feedSearchElement) }
            ScanningContract.Event.LoadingFeedClickEvent -> onLoadFeed()
            ScanningContract.Event.SearchFeedElementClickEvent -> onSearchFeedValueEvent()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onClearUiFieldsState(){
        _uiState.update { currentState ->
            currentState.copy(
                feedUrl = ""
            )
        }
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
        viewModelScope.launch {
            _uiState.update {  currentState ->
                currentState.copy(
                    isFeedScanningResponse = true,
                    loadedFeed = loadFeedUseCase.execute(feedUrl = _uiState.value.feedUrl)
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
            searchFeedElementUseCase.execute(searchedFeedElement = _uiState.value.feedSearchValue)
        }
    }

}