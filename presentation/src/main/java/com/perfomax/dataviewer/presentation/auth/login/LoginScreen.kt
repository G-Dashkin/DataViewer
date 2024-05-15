package com.perfomax.dataviewer.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.height100
import com.perfomax.dataviewer.ui.theme.height15
import com.perfomax.dataviewer.ui.theme.height40
import com.perfomax.dataviewer.ui.theme.height5
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding30
import com.perfomax.dataviewer.ui.theme.width100
import com.perfomax.dataviewer.ui.theme.width5
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.FormTextFieldAuth
import com.perfomax.ui.R

@Composable
fun LoginScreen(
    uiState: LoginContract.State,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToReset: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(width100)
                .height(width100),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo)
        )
        Text(text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(height100))
        Text(text = stringResource(id = R.string.login), fontSize = 22.sp)
        FormTextFieldAuth(
            text = uiState.login,
            labelText = stringResource(id = R.string.email),
            isError = uiState.loginError,
            errorMessage = uiState.loginErrorMessage,
            onChange = onEmailChange
        )
        Spacer(modifier = Modifier.height(height5))
        FormTextFieldAuth(
            text = uiState.password,
            labelText = stringResource(id = R.string.password),
            isError = uiState.passwordError,
            errorMessage = uiState.passwordErrorMessage,
            onChange = onPasswordChange
        )
        Spacer(modifier = Modifier.height(height15))
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(start = padding30, end = padding30)
            .height(height50)
            .requiredHeight(height40),
            shape = RoundedCornerShape(cornerShape8),
            contentPadding = PaddingValues(zeroVal),
            onClick = onLoginClicked
        ) {
            Text(text = stringResource(id = R.string.login),
                 color = MaterialTheme.colorScheme.onSecondary,
                 style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(height15))
        Row {
            Text(text = stringResource(id = R.string.not_member),
                 color = Color.Black,
                 style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(width5))
            Text(modifier = Modifier.clickable { onNavigateToRegister.invoke() },
                 text = stringResource(id = R.string.register),
                 color = Color.Black,
                 style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600))
            Spacer(modifier = Modifier.width(width5))
            Text(text = stringResource(id = R.string.here),
                 color = Color.Black,
                 style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(height5))
        Row {
            Text(text = stringResource(id = R.string.forgot_password),
                 color = Color.Black,
                 style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(width5))
            Text(modifier = Modifier.clickable { onNavigateToReset.invoke() },
                 text = stringResource(id = R.string.reset),
                 color = Color.Black,
                 style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600))
            Spacer(modifier = Modifier.width(width5))
            Text(text = stringResource(id = R.string.here),
                 color = Color.Black,
                 style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DataViewerTheme {
        LoginScreen(
            uiState = LoginContract.State.initial(),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClicked = {},
            onNavigateToRegister = {},
            onNavigateToReset = {}
        )
    }
}