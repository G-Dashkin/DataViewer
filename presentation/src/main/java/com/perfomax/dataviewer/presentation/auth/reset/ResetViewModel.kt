package com.perfomax.dataviewer.presentation.auth.reset

import android.app.Application
import androidx.lifecycle.ViewModel
import com.perfomax.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ResetViewModel @Inject constructor(
    private val context: Application
): ViewModel(), ResetContract {

    private val _uiState = MutableStateFlow(ResetContract.State.initial())
    private val _effect = MutableStateFlow<ResetContract.Effect?>(null)

    override val uiState: StateFlow<ResetContract.State> = _uiState.asStateFlow()
    override val effect: StateFlow<ResetContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: ResetContract.Event) {
        when(event) {
            is ResetContract.Event.EmailChangeEvent -> onEmailChange(event.email)
            ResetContract.Event.LoginEvent -> onLogin()
            ResetContract.Event.ResetEvent -> onReset()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onLogin() {
        _effect.update {
            ResetContract.Effect.Login
        }
    }

    private fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    private fun onReset() {
        val email = _uiState.value.email
        val emailValid = email.isNotEmpty()
        if (emailValid) {
            _uiState.update { currentState ->
                currentState.copy(
                    emailError = email.isEmpty()
                )
            }
        } else if(email.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    emailError = email.isEmpty(),
                    emailErrorMessage = context.applicationContext.getString(R.string.empty_email_field)
                )
            }
        }
    }
}