package com.dashkin.dataviewer.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkin.dataviewer.feature.search.presentation.state.SearchEvent
import com.dashkin.dataviewer.feature.search.presentation.state.SearchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SearchEvent>()
    val sideEffects: SharedFlow<SearchEvent> = _sideEffects.asSharedFlow()

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.LoadFile -> _state.update { it.copy(fileUri = event.fileUri) }
            is SearchEvent.QueryChanged -> _state.update { it.copy(query = event.query) }
            is SearchEvent.StartSearch -> startSearch()
            is SearchEvent.CancelSearch -> cancelSearch()
            is SearchEvent.DismissError -> _state.update { it.copy(error = null) }
            is SearchEvent.NavigateToNode -> emitEffect(event)
        }
    }

    private fun startSearch() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.update { it.copy(isSearching = true, results = emptyList(), searchProgress = 0f) }
            // Streaming search implementation will go here
            _state.update { it.copy(isSearching = false, searchProgress = 1f) }
        }
    }

    private fun cancelSearch() {
        searchJob?.cancel()
        _state.update { it.copy(isSearching = false) }
    }

    private fun emitEffect(event: SearchEvent) {
        viewModelScope.launch { _sideEffects.emit(event) }
    }
}
