package com.perfomax.dataviewer.presentation.auth.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.presentation.auth.login.LoginScreen
import com.perfomax.dataviewer.presentation.auth.login.LoginViewModel
import com.perfomax.dataviewer.presentation.auth.registration.RegisterScreen
import com.perfomax.dataviewer.presentation.auth.registration.RegisterViewModel
import com.perfomax.dataviewer.presentation.auth.reset.ResetScreen
import com.perfomax.dataviewer.presentation.auth.reset.ResetViewModel

const val AUTH_GRAPH = "authentication"

private object LoginDestination: NavigationDestination {
    override val route = "login"
}

private object RegisterDestination: NavigationDestination {
    override val route = "register"
}

private object ResetDestination: NavigationDestination {
    override val route = "reset"
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
            val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()
            LoginScreen(
                loginUiState = loginUiState,
                loginViewModel = loginViewModel,
                onLoginClicked = onLoginClicked,
                onNavigateToRegister = onNavigateToRegister,
                onNavigateToReset = onNavigateToReset
            )
        }

        composable(route = RegisterDestination.route) {
            val registerViewModel: RegisterViewModel = viewModel()
            val registerUpUiState by registerViewModel.signUpUiState.collectAsStateWithLifecycle()
            RegisterScreen(
                registerUiState = registerUpUiState,
                registerViewModel = registerViewModel,
                onNavigateToLogin = onNavigateToLogin,
            )
        }

        composable(route = ResetDestination.route) {
            val resetViewModel: ResetViewModel = viewModel()
            val resetUiState by resetViewModel.resetPasswordUiState.collectAsStateWithLifecycle()
            ResetScreen(
                resetUiState = resetUiState,
                resetViewModel = resetViewModel,
                onNavigateToLogin = onNavigateToLogin
            )
        }

    }
}
