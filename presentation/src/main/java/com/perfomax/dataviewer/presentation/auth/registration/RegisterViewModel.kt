package com.perfomax.dataviewer.presentation.auth.registration

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.presentation.auth.login.LoginContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel(), RegisterContract {

    private val _uiState = MutableStateFlow(RegisterContract.State.initial())
    private val _effect = MutableStateFlow<RegisterContract.Effect?>(null)

    override val uiState: StateFlow<RegisterContract.State> = _uiState.asStateFlow()
    override val effect: StateFlow<RegisterContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: RegisterContract.Event) {
        when(event) {
            is RegisterContract.Event.EmailChangeEvent -> {}
            is RegisterContract.Event.FirstNameChangeEvent -> {}
            is RegisterContract.Event.PasswordChangeEvent -> {}
            RegisterContract.Event.LoginEvent -> {}
            RegisterContract.Event.RegisterEvent -> {}
            RegisterContract.Event.ResetEvent -> {}
        }
    }

    override fun consume() {
        _effect.update { null }
    }
}