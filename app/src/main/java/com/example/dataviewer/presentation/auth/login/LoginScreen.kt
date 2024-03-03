package com.example.dataviewer.presentation.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dataviewer.core.ui.theme.DataViewerTheme

@Composable
fun LoginScreen(
    loginUiState: LoginUiState,
    loginViewModel: LoginViewModel,
    onLoginClicked: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToReset: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "LoginScreen", fontSize = 22.sp)
        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = onLoginClicked
        ) { Text(text = "Login", fontSize = 22.sp) }

        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = onNavigateToRegister
        ) {
            Text(text = "Register", fontSize = 22.sp)
        }
        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = onNavigateToReset
        ) {
            Text(text = "Reset", fontSize = 22.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DataViewerTheme {
        val loginViewModel: LoginViewModel = viewModel()
        val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()
        LoginScreen(
            loginUiState = loginUiState,
            loginViewModel = loginViewModel,
            onLoginClicked = {},
            onNavigateToRegister = {},
            onNavigateToReset = {}
        )
    }
}