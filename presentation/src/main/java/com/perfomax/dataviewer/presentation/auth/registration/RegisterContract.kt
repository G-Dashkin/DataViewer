package com.perfomax.dataviewer.presentation.auth.registration

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface RegisterContract:
    UnidirectionalViewModel<RegisterContract.Event, RegisterContract.State, RegisterContract.Effect?> {

    data class State(
        val firstName: String,
        val firstNameError: Boolean,
        val firstNameErrorMessage: String,
        val email: String,
        val emailError: Boolean,
        val emailErrorMessage: String,
        val password: String,
        val passwordError: Boolean,
        val passwordErrorMessage: String
    ) {
        companion object {
            fun initial(): State = State(
                firstName = EMPTY,
                firstNameError = false,
                firstNameErrorMessage = EMPTY,
                email = EMPTY,
                emailError = false,
                emailErrorMessage = EMPTY,
                password = EMPTY,
                passwordError = false,
                passwordErrorMessage = EMPTY
            )
            fun notRegister(): State = State(
                firstName = EMPTY,
                firstNameError = false,
                firstNameErrorMessage = EMPTY,
                email = EMPTY,
                emailError = false,
                emailErrorMessage = EMPTY,
                password = EMPTY,
                passwordError = false,
                passwordErrorMessage = EMPTY
            )
        }
    }

    sealed interface Event {
        data object LoginEvent: Event
        data object RegisterEvent: Event
        data object ResetEvent: Event
        data class FirstNameChangeEvent(val firstName: String): Event
        data class EmailChangeEvent(val email: String): Event
        data class PasswordChangeEvent(val password: String): Event
    }

    sealed interface Effect {
        data object Login : Effect

    }
}