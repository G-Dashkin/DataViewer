package com.perfomax.dataviewer.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.auth.GetAuthUseCase
import com.perfomax.dataviewer.domain.usecases.auth.GetUsersUseCase
import com.perfomax.dataviewer.domain.usecases.auth.RegisterUseCase
import com.perfomax.dataviewer.domain.usecases.auth.SetAuthUseCase
import com.perfomax.dataviewer.presentation.auth.registration.RegisterContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
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
                        Log.d("MyLog", "Пользователь не зарегистрирован")
                    } else if ( user.password != password) {
                        Log.d("MyLog", "Пароль не верный")
                    } else {
                        Log.d("MyLog", "Выполнение юзкейса Login, сохранение залогиненно пользователя и переход на экран Home")
                        setAuthUseCase.execute(user.userName)
                        _effect.update {
                            LoginContract.Effect.Login
                        }
                    }
                }
            } else {
                _uiState.update { currentState ->
                    LoginContract.State.notLogin()
                    currentState.copy(
                        loginError = email.isEmpty(),
                        passwordError = password.isEmpty()
                    )
                }
            }
        }
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