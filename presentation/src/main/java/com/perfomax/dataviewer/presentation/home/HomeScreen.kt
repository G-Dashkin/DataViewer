package com.perfomax.dataviewer.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.perfomax.dataviewer.domain.utils.getFeedName
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.widgets.FeedItem

@Composable
fun HomeScreen(
    uiState: HomeContract.State,
    onUpdateFeedsClick:() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .defaultMinSize(minHeight = 50.dp),
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(
                start = 0.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 0.dp,
            ),
            onClick = onUpdateFeedsClick
        ) { Text(text = "Обновить фиды", fontSize = 18.sp) }

        Log.d("MyLog", uiState.feedsList.toString())
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.feedsList) { element ->
                FeedItem(
                    feedElement = element.getFeedName(),
                    onRemove = {},
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DataViewerTheme {
        val homeViewModel: HomeViewModel = viewModel()
        val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
        val homeEffects by homeViewModel.effect.collectAsStateWithLifecycle()
        HomeScreen (
            uiState = homeUiState,
            onUpdateFeedsClick = {}
        )
    }
}