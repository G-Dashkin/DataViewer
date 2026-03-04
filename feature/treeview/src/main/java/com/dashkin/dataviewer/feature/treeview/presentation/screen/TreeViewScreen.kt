package com.dashkin.dataviewer.feature.treeview.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dashkin.dataviewer.feature.treeview.presentation.state.TreeViewEvent
import com.dashkin.dataviewer.feature.treeview.presentation.viewmodel.TreeViewViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeViewScreen(
    fileUri: String,
    onNavigateBack: () -> Unit,
    onNavigateToSearch: (String) -> Unit,
    onNavigateToNodeDetail: (String, String) -> Unit,
    viewModel: TreeViewViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(fileUri) {
        viewModel.onEvent(TreeViewEvent.LoadFile(fileUri))
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { event ->
            when (event) {
                is TreeViewEvent.NavigateToSearch -> onNavigateToSearch(event.fileUri)
                is TreeViewEvent.NavigateToNodeDetail ->
                    onNavigateToNodeDetail(event.nodeId, event.fileUri)
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.fileName.ifBlank { "Tree View" }) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(TreeViewEvent.NavigateToSearch(fileUri))
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(state.error!!)
                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.nodes, key = { it.id }) { node ->
                        // TreeNodeItem will go here
                        Text(node.displayKey)
                    }
                }
            }
        }
    }
}
