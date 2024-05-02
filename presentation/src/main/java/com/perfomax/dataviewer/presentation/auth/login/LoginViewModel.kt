package com.perfomax.dataviewer.presentation.auth.login

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
            is LoginContract.Event.EmailChangeEvent -> {}
            is LoginContract.Event.PasswordChangeEvent -> {}
            LoginContract.Event.LoginEvent -> {}
            LoginContract.Event.RegisterEvent -> {}
            LoginContract.Event.ResetEvent -> {}
        }
    }

    override fun consume() {
        _effect.update { null }
    }

}