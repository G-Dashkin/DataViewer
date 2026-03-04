package com.dashkin.dataviewer.feature.search.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.dashkin.dataviewer.feature.search.presentation.state.SearchEvent
import com.dashkin.dataviewer.feature.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    fileUri: String,
    onNavigateBack: () -> Unit,
    onNavigateToNode: (String, String) -> Unit,
    viewModel: SearchViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(fileUri) {
        viewModel.onEvent(SearchEvent.LoadFile(fileUri))
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { event ->
            if (event is SearchEvent.NavigateToNode) {
                onNavigateToNode(event.nodeId, event.fileUri)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchBar(
                query = state.query,
                onQueryChange = { viewModel.onEvent(SearchEvent.QueryChanged(it)) },
                onSearch = { viewModel.onEvent(SearchEvent.StartSearch) },
                active = false,
                onActiveChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search keys and values…") }
            ) {}

            if (state.isSearching) {
                LinearProgressIndicator(
                    progress = { state.searchProgress },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.results, key = { it.node.id }) { result ->
                    ListItem(
                        headlineContent = { Text(result.node.displayKey) },
                        supportingContent = { Text(result.jsonPath) },
                        overlineContent = { Text(result.matchedField.name) }
                    )
                }
            }
        }
    }
}
