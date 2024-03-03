package com.example.dataviewer.presentation.auth.registration

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.dataviewer.domain.EMPTY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Immutable
data class RegisterUiState(
    val name: String,
    val nameError: Boolean,
    val email: String,
    val emailError: Boolean,
    val password: String,
    val passwordError: Boolean
)

class RegisterViewModel: ViewModel() {

    private val _signUpUiState = MutableStateFlow(
        RegisterUiState(
            name = EMPTY,
            nameError = false,
            email = EMPTY,
            emailError = false,
            password = EMPTY,
            passwordError = false
        )
    )

    val signUpUiState: StateFlow<RegisterUiState> = _signUpUiState

    fun onNameChange(value: String) {
        _signUpUiState.update { currentState ->
            currentState.copy(
                name = value,
                nameError = value.isBlank())
        }
    }

    fun onEmailChange(email: String) {
        _signUpUiState.update { currentState ->
            currentState.copy(
                email = email,
                emailError = email.isNotBlank()
            )
        }
    }

    fun onPasswordChange(password: String) {
        _signUpUiState.update { currentState ->
            currentState.copy(
                password = password,
                passwordError = password.isNotBlank()
            )
        }
    }

    fun onSignUpSubmit() {
        _signUpUiState.update { currentState ->
            currentState.copy(
                nameError = currentState.name.isBlank(),
                emailError = currentState.email.isBlank(),
                passwordError = currentState.name.isBlank(),
            )
        }
    }
}