package com.perfomax.dataviewer.presentation.auth.reset

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.presentation.auth.login.LoginContract
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.height100
import com.perfomax.dataviewer.ui.theme.height15
import com.perfomax.dataviewer.ui.theme.height40
import com.perfomax.dataviewer.ui.theme.height5
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.padding30
import com.perfomax.dataviewer.ui.theme.width100
import com.perfomax.dataviewer.ui.theme.width5
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.FormTextFieldDefault
import com.perfomax.ui.R

@Composable
fun ResetScreen(
    uiState: ResetContract.State,
    onEmailChange: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.width(width100)
                               .height(width100),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo)
        )
        Text(text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(height100))
        Text(text = stringResource(id = R.string.reset), fontSize = 22.sp)

        FormTextFieldDefault(
            modifier = Modifier.fillMaxWidth()
                .defaultMinSize(minHeight = height50)
                .background(color = MaterialTheme.colorScheme.background)
                .padding(start = padding15, end = padding15),
            text = uiState.email,
            labelText = stringResource(id = R.string.email),
            isError = false,
            onChange = onEmailChange
        )
        Spacer(modifier = Modifier.height(height15))
        Button(modifier = Modifier.fillMaxWidth()
            .padding(start = padding30, end = padding30)
            .height(height50)
            .requiredHeight(height40),
            shape = RoundedCornerShape(cornerShape8),
            contentPadding = PaddingValues(zeroVal),
            onClick = {}
        ) {
            Text(text = stringResource(id = R.string.send),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(height15))
        Row {
            Text(text = stringResource(id = R.string.already_member),
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(width5))
            Text(modifier = Modifier.clickable { onNavigateToLogin.invoke() },
                text = stringResource(id = R.string.login),
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
fun ResetScreenPreview() {
    DataViewerTheme {
        ResetScreen(
            uiState = ResetContract.State.initial(),
            onEmailChange = {},
            onNavigateToLogin = {}
        )
    }
}