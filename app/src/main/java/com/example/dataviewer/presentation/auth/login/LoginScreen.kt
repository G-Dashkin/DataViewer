package com.example.dataviewer.presentation.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dataviewer.core.ui.theme.DataViewerTheme

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "LoginScreen", fontSize = 22.sp)
        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = {  }
        ) { Text(text = "Login", fontSize = 22.sp) }

        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = {  }
        ) {
            Text(text = "Register", fontSize = 22.sp)
        }
        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = {  }
        ) {
            Text(text = "Reset", fontSize = 22.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DataViewerTheme {
        LoginScreen()
    }
}