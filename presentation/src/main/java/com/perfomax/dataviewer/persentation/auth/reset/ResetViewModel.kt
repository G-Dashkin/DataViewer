package com.perfomax.dataviewer.persentation.auth.reset

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.domain.EMPTY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Immutable
data class ResetUiState(
    val email: String,
    val emailError: Boolean
)

class ResetViewModel: ViewModel() {

    private val _resetPasswordUiState = MutableStateFlow(
        ResetUiState(
            email = EMPTY,
            emailError = false
        )
    )

    val resetPasswordUiState: StateFlow<ResetUiState> = _resetPasswordUiState

    fun onEmailChange(email: String) {
        _resetPasswordUiState.update { currentState ->
            currentState.copy(
                email = email,
                emailError = email.isNotBlank()
            )
        }
    }

    fun onResetPasswordSubmit() {
        _resetPasswordUiState.update { currentState ->
            currentState.copy(
                emailError = currentState.email.isBlank()
            )
        }
    }
}