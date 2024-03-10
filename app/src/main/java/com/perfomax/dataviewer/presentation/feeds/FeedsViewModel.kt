package com.perfomax.dataviewer.presentation.feeds

import android.util.Log
import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.presentation.home.HomeContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FeedsViewModel: ViewModel(), FeedsContract {

    private val _uiState = MutableStateFlow(FeedsContract.State.initial())
    override val uiState: StateFlow<FeedsContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<FeedsContract.Effect?>(null)
    override val effect: StateFlow<FeedsContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: FeedsContract.Event) {
        when(event) {
            is FeedsContract.Event.FeedUrlChangeEvent -> onFeedUrlChangeEvent(event.text)
            FeedsContract.Event.AddFeedClickEvent -> onAddFeedClick()
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
        Log.d("MyLog", "ClickTest onAddFeedClick")
    }


}