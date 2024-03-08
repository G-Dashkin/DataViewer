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

@Immutable
data class TextFieldUiState(
    val text: String,
    val textError: Boolean
)

class HomeViewModel: ViewModel(), HomeContract {

    private val _uiState = MutableStateFlow(HomeContract.State.initial())
    override val uiState: StateFlow<HomeContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<HomeContract.Effect?>(null)
    override val effect: StateFlow<HomeContract.Effect?> = _effect.asStateFlow()

    override fun event(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.TextChangeEvent -> onTextFieldsChange(event.text)
            HomeContract.Event.ClickEvent -> onClickTest()
        }
    }

    override fun consume() {
        _effect.update { null }
    }


    private val _textFieldUiState = MutableStateFlow(
        TextFieldUiState(
            text = EMPTY,
            textError = false
        )
    )

    val textFieldUiState: StateFlow<TextFieldUiState> = _textFieldUiState

    private fun onTextFieldsChange(text: String) {
        _textFieldUiState.update { currentState ->
            currentState.copy(
                text = text,
                textError = text.isNotBlank()
            )
        }
    }

    fun onClickTest() {
        Log.d("MyLog", "ClickTest")
    }


}