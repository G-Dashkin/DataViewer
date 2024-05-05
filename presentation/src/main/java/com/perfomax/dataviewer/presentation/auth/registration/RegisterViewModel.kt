package com.perfomax.dataviewer.presentation.auth.registration

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.domain.models.User
import com.perfomax.dataviewer.domain.usecases.auth.GetUsersUseCase
import com.perfomax.dataviewer.domain.usecases.auth.RegisterUseCase
import com.perfomax.dataviewer.domain.usecases.settings.SetUpdatePeriodUseCase
import com.perfomax.dataviewer.domain.utils.getUserName
import com.perfomax.dataviewer.presentation.auth.login.LoginContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val getUsersUseCase: GetUsersUseCase
): ViewModel(), RegisterContract {

    private val _uiState = MutableStateFlow(RegisterContract.State.initial())
    private val _effect = MutableStateFlow<RegisterContract.Effect?>(null)

    override val uiState: StateFlow<RegisterContract.State> = _uiState.asStateFlow()
    override val effect: StateFlow<RegisterContract.Effect?> = _effect.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }

    override fun intent(event: RegisterContract.Event) {
        when(event) {
            is RegisterContract.Event.EmailChangeEvent -> onEmailChange(event.email)
            is RegisterContract.Event.FirstNameChangeEvent -> onFirstNameChange(event.firstName)
            is RegisterContract.Event.PasswordChangeEvent -> onPasswordChange(event.password)
            RegisterContract.Event.RegisterEvent -> onRegister()
            RegisterContract.Event.LoginEvent -> onLogin()
            RegisterContract.Event.ResetEvent -> {}
        }
    }

    private fun onLogin() {
        _effect.update {
            RegisterContract.Effect.Login
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailError = email.isNotEmpty()
            )
        }
    }

    private fun onFirstNameChange(name: String) {
        _uiState.update {
            it.copy(
                firstName = name,
                firstNameError = name.isEmpty()
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

    private fun onRegister() {
        val newUser = "userName:${_uiState.value.firstName};" +
                      "email:${_uiState.value.email};" +
                      "password:${_uiState.value.password}"

        viewModelScope.launch {
            val allUsers = getUsersUseCase.execute()
            var newUserNameIsUnique = true
            if (allUsers.isEmpty()) {
                registerUseCase.execute(newUser)
                onLogin()
            } else {
                allUsers.forEach {
                    if (it.userName.lowercase(Locale.ROOT) == newUser.getUserName().lowercase(Locale.ROOT)) {
                        newUserNameIsUnique = false
                    }
                }
                if (newUserNameIsUnique) {
                    registerUseCase.execute(newUser)
                    onLogin()
                }
            }
        }
    }
}