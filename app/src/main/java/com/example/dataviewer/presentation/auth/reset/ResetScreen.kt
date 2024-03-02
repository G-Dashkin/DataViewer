package com.example.dataviewer.presentation.auth.reset

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.presentation.auth.registration.RegisterScreen

@Composable
fun ResetScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "ResetScreen", fontSize = 22.sp)
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
    }
}

@Preview(showBackground = true)
@Composable
fun ResetScreenPreview() {
    DataViewerTheme {
        ResetScreen()
    }
}