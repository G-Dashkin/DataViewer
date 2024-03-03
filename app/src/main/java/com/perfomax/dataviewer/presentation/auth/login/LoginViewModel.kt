package com.perfomax.dataviewer.presentation.auth.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.domain.EMPTY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Immutable
data class LoginUiState(
    val email: String,
    val emailError: Boolean,
    val password: String,
    val passwordError: Boolean
)

class LoginViewModel: ViewModel() {

    private val _loginUiState = MutableStateFlow(
        LoginUiState(
            email = EMPTY,
            emailError = false,
            password = EMPTY,
            passwordError = false
        )
    )

    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun onEmailChange(email: String) {
        _loginUiState.update { currentState ->
            currentState.copy(
                email = email,
                emailError = email.isNotBlank()
            )
        }
    }

    fun onPasswordChange(password: String) {
        _loginUiState.update { currentState ->
            currentState.copy(
                password = password,
                passwordError = password.isNotBlank()
            )
        }
    }

}