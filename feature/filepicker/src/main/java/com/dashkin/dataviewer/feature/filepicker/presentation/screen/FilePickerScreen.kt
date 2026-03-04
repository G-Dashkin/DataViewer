package com.dashkin.dataviewer.feature.filepicker.presentation.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dashkin.dataviewer.feature.filepicker.presentation.state.FilePickerEvent
import com.dashkin.dataviewer.feature.filepicker.presentation.viewmodel.FilePickerViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilePickerScreen(
    onNavigateToTreeView: (String) -> Unit,
    viewModel: FilePickerViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.toString()?.let { viewModel.onEvent(FilePickerEvent.FileSelected(it)) }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { event ->
            when (event) {
                is FilePickerEvent.OpenFilePicker -> filePicker.launch(
                    arrayOf("application/json", "text/xml", "application/xml")
                )
                is FilePickerEvent.NavigateToTreeView -> onNavigateToTreeView(event.fileUri)
                else -> Unit
            }
        }
    }

    LaunchedEffect(state.error) {
        state.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onEvent(FilePickerEvent.DismissError)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("DataViewer") }) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Open a JSON or XML file",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = { viewModel.onEvent(FilePickerEvent.OpenFilePicker) }) {
                        Text("Choose File")
                    }
                }
            }
        }
    }
}
