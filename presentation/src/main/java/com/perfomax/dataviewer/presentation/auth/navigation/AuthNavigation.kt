package com.perfomax.dataviewer.presentation.auth.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.auth.login.LoginContract
import com.perfomax.dataviewer.presentation.auth.login.LoginScreen
import com.perfomax.dataviewer.presentation.auth.login.LoginViewModel
import com.perfomax.dataviewer.presentation.auth.registration.RegisterContract
import com.perfomax.dataviewer.presentation.auth.registration.RegisterScreen
import com.perfomax.dataviewer.presentation.auth.registration.RegisterViewModel
import com.perfomax.dataviewer.presentation.auth.reset.ResetContract
import com.perfomax.dataviewer.presentation.auth.reset.ResetScreen
import com.perfomax.dataviewer.presentation.auth.reset.ResetViewModel
import com.perfomax.dataviewer.presentation.home.HomeViewModel
import com.perfomax.dataviewer.ui.base.useEffects
import com.perfomax.ui.R

const val AUTH_GRAPH = "authentication"

object LoginDestination: NavigationDestination {
    override val route = "login"
}

private object RegisterDestination: NavigationDestination {
    override val route = "register"
}

private object ResetDestination: NavigationDestination {
    override val route = "reset"
}

object AuthenticationTopLevelDestination: TopLevelDestination {
    override val route = LoginDestination.route
    override val iconId = R.drawable.ic_account
    override val titleId = R.string.account
}

fun NavHostController.navigateToLogin() {
    navigate(LoginDestination.route)
}

fun NavHostController.navigateToReset() {
    navigate(ResetDestination.route)
}

fun NavHostController.navigateToRegister() {
    navigate(RegisterDestination.route)
}

fun NavHostController.logout() {
    popBackStack()
    navigateToLogin()
}

fun NavGraphBuilder.authentication(
    onLoginClicked: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToReset: () -> Unit
){0
    navigation(startDestination = LoginDestination.route, route = AUTH_GRAPH){

        composable(route = LoginDestination.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loginUiState by loginViewModel.uiState.collectAsStateWithLifecycle()
            val loginEffects by loginViewModel.effect.collectAsStateWithLifecycle()

            loginViewModel.useEffects {
                when(loginEffects) {
                    LoginContract.Effect.Login -> onLoginClicked()
                    LoginContract.Effect.Register -> onNavigateToRegister()
                    LoginContract.Effect.Reset -> onNavigateToReset()
                    null -> Unit
                }
            }

            LoginScreen(
                uiState = loginUiState,
                onEmailChange = { email ->
                    loginViewModel.intent(LoginContract.Event.EmailChangeEvent(email))
                },
                onPasswordChange = { password ->
                    loginViewModel.intent(LoginContract.Event.PasswordChangeEvent(password))
                },
                onLoginClicked = { loginViewModel.intent(LoginContract.Event.LoginEvent) },
                onNavigateToRegister = { loginViewModel.intent(LoginContract.Event.RegisterEvent) },
                onNavigateToReset = { loginViewModel.intent(LoginContract.Event.ResetEvent) },
            )
        }

        composable(route = RegisterDestination.route) {
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            val registerUiState by registerViewModel.uiState.collectAsStateWithLifecycle()
            val registerEffects by registerViewModel.effect.collectAsStateWithLifecycle()

            registerViewModel.useEffects {
                when(registerEffects) {
                    RegisterContract.Effect.Login -> onNavigateToLogin()
                    null -> Unit
                }
            }

            RegisterScreen(
                uiState = registerUiState,
                onFirstName = { first ->
                    registerViewModel.intent(RegisterContract.Event.FirstNameChangeEvent(first))
                },
                onEmailChange = { email ->
                    registerViewModel.intent(RegisterContract.Event.EmailChangeEvent(email))
                },
                onPasswordChange = { password ->
                    registerViewModel.intent(RegisterContract.Event.PasswordChangeEvent(password))
                },
                onRegister = { registerViewModel.intent(RegisterContract.Event.RegisterEvent) },
                onNavigateToLogin = { registerViewModel.intent(RegisterContract.Event.LoginEvent) }
            )
        }

        composable(route = ResetDestination.route) {
            val resetViewModel = hiltViewModel<ResetViewModel>()
            val resetUiState by resetViewModel.uiState.collectAsStateWithLifecycle()
            val registerEffects by resetViewModel.effect.collectAsStateWithLifecycle()

            resetViewModel.useEffects {
                when(registerEffects) {
                    ResetContract.Effect.Login -> onNavigateToLogin()
                    null -> Unit
                }
            }

            ResetScreen(
                uiState = resetUiState,
                onEmailChange = { email ->
                    resetViewModel.intent(ResetContract.Event.EmailChangeEvent(email))
                },
                onNavigateToLogin = { resetViewModel.intent(ResetContract.Event.LoginEvent) },
                onResetClicked = { resetViewModel.intent(ResetContract.Event.ResetEvent) }
            )
        }

    }
}
