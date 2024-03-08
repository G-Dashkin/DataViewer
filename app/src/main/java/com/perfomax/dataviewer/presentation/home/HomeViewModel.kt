package com.perfomax.dataviewer.presentation.home

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.domain.EMPTY
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


sealed interface MenuUiState {
//    data object Loading : MenuUiState
    object Loading : MenuUiState

    @Immutable
    data class Success(
        val feedsList: ImmutableList<String>,
        val selectedSectionIndex: Int = 0,
//        val product: ImmutableList<MenuItem>
    ): MenuUiState {
        fun selectionTitle(): String = feedsList[selectedSectionIndex]
    }
}

class HomeViewModel: ViewModel(), HomeContract {

    private val _uiState = MutableStateFlow(HomeContract.State.initial())
    override val uiState: StateFlow<HomeContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<HomeContract.Effect?>(null)
    override val effect: StateFlow<HomeContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.TextChangeEvent -> onTextFieldsChange(event.text)
            HomeContract.Event.ClickEvent -> onClickTest()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onTextFieldsChange(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                text = text,
                textError = text.isNotBlank()
            )
        }
    }

    private fun onClickTest() {
        Log.d("MyLog", "ClickTest")
    }


}