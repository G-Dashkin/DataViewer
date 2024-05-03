package com.perfomax.dataviewer.presentation.auth.navigation

import android.util.Log
import androidx.compose.runtime.getValue
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
){
    navigation(startDestination = LoginDestination.route, route = AUTH_GRAPH){

        composable(route = LoginDestination.route) {

            val loginViewModel: LoginViewModel = viewModel()
            val loginUiState by loginViewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                uiState = loginUiState,
                onEmailChange = { email ->
                    Log.d("MyLog", email)
                    loginViewModel.intent(LoginContract.Event.EmailChangeEvent(email))
                },
                onPasswordChange = { password ->
                    loginViewModel.intent(LoginContract.Event.PasswordChangeEvent(password))
                },
                onLoginClicked = onLoginClicked,
                onNavigateToRegister = onNavigateToRegister,
                onNavigateToReset = onNavigateToReset,
            )
        }

        composable(route = RegisterDestination.route) {
            val registerViewModel: RegisterViewModel = viewModel()
            val registerUiState by registerViewModel.uiState.collectAsStateWithLifecycle()
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
                onNavigateToLogin = onNavigateToLogin

            )
        }

        composable(route = ResetDestination.route) {
            val resetViewModel: ResetViewModel = viewModel()
            val resetUiState by resetViewModel.uiState.collectAsStateWithLifecycle()
            ResetScreen(
                uiState = resetUiState,
                onEmailChange = { email ->
                    resetViewModel.intent(ResetContract.Event.EmailChangeEvent(email))
                },
                onNavigateToLogin = onNavigateToLogin
            )
        }

    }
}
