package com.perfomax.dataviewer.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel(), LoginContract {

    private val _uiState = MutableStateFlow(LoginContract.State.initial())
    private val _effect = MutableStateFlow<LoginContract.Effect?>(null)

    override val uiState: StateFlow<LoginContract.State> = _uiState.asStateFlow()
    override val effect: StateFlow<LoginContract.Effect?> = _effect.asStateFlow()


    override fun intent(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.EmailChangeEvent -> onEmailChange(event.email)
            is LoginContract.Event.PasswordChangeEvent -> onPasswordChange(event.password)
            LoginContract.Event.LoginEvent -> {}
            LoginContract.Event.RegisterEvent -> {}
            LoginContract.Event.ResetEvent -> {}
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                login = email,
                loginError = email.isNotEmpty()
            )
        }
    }

    private fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = password.isNotEmpty()
            )
        }
    }

}