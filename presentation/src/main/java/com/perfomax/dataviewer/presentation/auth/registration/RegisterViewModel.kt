package com.perfomax.dataviewer.presentation.auth.registration

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.auth.GetUsersUseCase
import com.perfomax.dataviewer.domain.usecases.auth.RegisterUseCase
import com.perfomax.dataviewer.domain.utils.getEmail
import com.perfomax.ui.R
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
    private val context: Application,
    private val registerUseCase: RegisterUseCase,
    private val getUsersUseCase: GetUsersUseCase
): ViewModel(), RegisterContract {

    private val _uiState = MutableStateFlow(RegisterContract.State.initial())
    private val _effect = MutableStateFlow<RegisterContract.Effect?>(null)

    override val uiState: StateFlow<RegisterContract.State> = _uiState.asStateFlow()
    override val effect: StateFlow<RegisterContract.Effect?> = _effect.asStateFlow()

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

    override fun consume() {
        _effect.update { null }
    }

    private fun onLogin() {
        _effect.update {
            RegisterContract.Effect.Login
        }
    }

    private fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    private fun onFirstNameChange(name: String) {
        _uiState.update {
            it.copy(
                firstName = name
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

    private fun onRegister() {
        val newUser = "userName:${_uiState.value.firstName};" +
                      "email:${_uiState.value.email};" +
                      "password:${_uiState.value.password}"

        val email = _uiState.value.email
        val firstName = _uiState.value.firstName
        val password = _uiState.value.password

        val emailValid = email.isNotEmpty()
        val firstNameValid = firstName.isNotEmpty()
        val passwordValid = password.isNotEmpty()

        viewModelScope.launch {
            val allUsers = getUsersUseCase.execute()
            var newUserNameIsUnique = true

            if (firstNameValid && emailValid && passwordValid) {
                if (allUsers.isEmpty()) {
                    registerUseCase.execute(newUser)
                    onLogin()
                } else {
                    allUsers.forEach {
                        if (it.email.lowercase(Locale.ROOT) == newUser.getEmail().lowercase(Locale.ROOT)) {
                            newUserNameIsUnique = false
                        }
                    }
                    if (newUserNameIsUnique) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                emailError = email.isEmpty(),
                                firstNameError = firstName.isEmpty(),
                                passwordError = password.isEmpty()
                            )
                        }
                        registerUseCase.execute(newUser)
                        onLogin()
                    } else {
                        _uiState.update { currentState ->
                            RegisterContract.State.notRegister()
                            currentState.copy(
                                emailError = true,
                                emailErrorMessage = context.applicationContext.getString(R.string.empty_email_field),
                            )
                        }
                    }
                }
            } else if (firstName.isEmpty() && email.isEmpty() && password.isEmpty()) {
                _uiState.update { currentState ->
                    RegisterContract.State.notRegister()
                    currentState.copy(
                        firstNameError = firstName.isEmpty(),
                        firstNameErrorMessage = context.applicationContext.getString(R.string.empty_first_name_field),
                        emailError = email.isEmpty(),
                        emailErrorMessage = context.applicationContext.getString(R.string.empty_email_field),
                        passwordError = password.isEmpty(),
                        passwordErrorMessage = context.applicationContext.getString(R.string.empty_password_field)
                    )
                }
            } else if(email.isEmpty() && password.isEmpty()) {
                _uiState.update { currentState ->
                    RegisterContract.State.notRegister()
                    currentState.copy(
                        firstNameError = firstName.isEmpty(),
                        emailError = email.isEmpty(),
                        emailErrorMessage = context.applicationContext.getString(R.string.empty_email_field),
                        passwordError = password.isEmpty(),
                        passwordErrorMessage = context.applicationContext.getString(R.string.empty_password_field)
                    )
                }
            } else if(firstName.isEmpty() && password.isEmpty()) {
                _uiState.update { currentState ->
                    RegisterContract.State.notRegister()
                    currentState.copy(
                        firstNameError = firstName.isEmpty(),
                        firstNameErrorMessage = context.applicationContext.getString(R.string.empty_first_name_field),
                        emailError = email.isEmpty(),
                        passwordError = password.isEmpty(),
                        passwordErrorMessage = context.applicationContext.getString(R.string.empty_password_field)
                    )
                }
            } else if(firstName.isEmpty() && email.isEmpty()) {
                _uiState.update { currentState ->
                    RegisterContract.State.notRegister()
                    currentState.copy(
                        firstNameError = firstName.isEmpty(),
                        firstNameErrorMessage = context.applicationContext.getString(R.string.empty_first_name_field),
                        emailError = email.isEmpty(),
                        emailErrorMessage = context.applicationContext.getString(R.string.empty_email_field),
                        passwordError = password.isEmpty()
                    )
                }
            } else if(firstName.isEmpty()) {
                _uiState.update { currentState ->
                    RegisterContract.State.notRegister()
                    currentState.copy(
                        firstNameError = firstName.isEmpty(),
                        firstNameErrorMessage = context.applicationContext.getString(R.string.empty_first_name_field),
                        emailError = email.isEmpty(),
                        passwordError = password.isEmpty()
                    )
                }
            } else if(email.isEmpty()) {
                _uiState.update { currentState ->
                    RegisterContract.State.notRegister()
                    currentState.copy(
                        firstNameError = firstName.isEmpty(),
                        emailError = email.isEmpty(),
                        emailErrorMessage = context.applicationContext.getString(R.string.empty_email_field),
                        passwordError = password.isEmpty()
                    )
                }
            } else if (password.isEmpty()){
                _uiState.update { currentState ->
                    RegisterContract.State.notRegister()
                    currentState.copy(
                        firstNameError = firstName.isEmpty(),
                        emailError = email.isEmpty(),
                        passwordError = password.isEmpty(),
                        passwordErrorMessage = context.applicationContext.getString(R.string.empty_password_field)
                    )
                }
            }
        }
    }
}