package com.perfomax.dataviewer.presentation.auth.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.auth.GetUsersUseCase
import com.perfomax.dataviewer.domain.usecases.auth.SetAuthUseCase
import com.perfomax.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val context: Application,
    private val getUsersUseCase: GetUsersUseCase,
    private val setAuthUseCase: SetAuthUseCase
): ViewModel(), LoginContract {

    private val _uiState = MutableStateFlow(LoginContract.State.initial())
    private val _effect = MutableStateFlow<LoginContract.Effect?>(null)

    override val uiState: StateFlow<LoginContract.State> = _uiState.asStateFlow()
    override val effect: StateFlow<LoginContract.Effect?> = _effect.asStateFlow()


    override fun intent(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.EmailChangeEvent -> onEmailChange(event.email)
            is LoginContract.Event.PasswordChangeEvent -> onPasswordChange(event.password)
            LoginContract.Event.LoginEvent -> onLogin()
            LoginContract.Event.RegisterEvent -> onRegister()
            LoginContract.Event.ResetEvent -> onReset()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onRegister() {
        _effect.update {
            LoginContract.Effect.Register
        }
    }

    private fun onReset() {
        _effect.update {
            LoginContract.Effect.Reset
        }
    }

    private fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                login = email
            )
        }
    }

    private fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }

    private fun onLogin() {
        viewModelScope.launch {
            val allUsers = getUsersUseCase.execute()
            val email = _uiState.value.login
            val password = _uiState.value.password

            val emailValid = email.isNotEmpty()
            val passwordValid = password.isNotEmpty()

            if (emailValid && passwordValid) {
                allUsers.forEach { user ->
                    if (user.email != email) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loginErrorMessage = context.applicationContext.getString(R.string.email_error),
                                loginError = true,
                                passwordError = false
                            )
                        }
                    } else if ( user.password != password) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                passwordErrorMessage = context.applicationContext.getString(R.string.password_error),
                                passwordError = true,
                                loginError = false
                            )
                        }
                    } else {
                        _uiState.update { currentState ->
                            currentState.copy(
                                passwordError = false,
                                loginError = false
                            )
                        }
                        setAuthUseCase.execute(user.userName)
                        _effect.update {
                            LoginContract.Effect.Login
                        }
                    }
                }
            } else if (email.isEmpty() && password.isEmpty()) {
                _uiState.update { currentState ->
                    LoginContract.State.notLogin()
                    currentState.copy(
                        loginError = email.isEmpty(),
                        loginErrorMessage = context.applicationContext.getString(R.string.empty_email_field),
                        passwordError = password.isEmpty(),
                        passwordErrorMessage = context.applicationContext.getString(R.string.empty_password_field)
                    )
                }
            } else if(email.isEmpty()) {
                _uiState.update { currentState ->
                    LoginContract.State.notLogin()
                    currentState.copy(
                        loginError = email.isEmpty(),
                        passwordError = password.isEmpty(),
                        loginErrorMessage = context.applicationContext.getString(R.string.empty_email_field)
                    )
                }
            } else if(password.isEmpty()) {
                _uiState.update { currentState ->
                    LoginContract.State.notLogin()
                    currentState.copy(
                        passwordError = password.isEmpty(),
                        loginError = email.isEmpty(),
                        passwordErrorMessage = context.applicationContext.getString(R.string.empty_password_field)
                    )
                }
            }
        }
    }

}