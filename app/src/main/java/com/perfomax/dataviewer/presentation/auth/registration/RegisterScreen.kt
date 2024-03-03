package com.perfomax.dataviewer.presentation.auth.registration

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
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme


@Composable
fun RegisterScreen(
    registerUiState: RegisterUiState,
    registerViewModel: RegisterViewModel,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "RegisterScreen", fontSize = 22.sp)
        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = onNavigateToLogin
        ) {
            Text(text = "Login", fontSize = 22.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val registerViewModel: RegisterViewModel = viewModel()
    val registerUiState by registerViewModel.signUpUiState.collectAsStateWithLifecycle()
    DataViewerTheme {
        RegisterScreen(
            registerUiState = registerUiState,
            registerViewModel = registerViewModel,
            onNavigateToLogin = {}
        )
    }
}