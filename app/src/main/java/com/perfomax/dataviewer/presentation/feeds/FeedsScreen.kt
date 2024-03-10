package com.perfomax.dataviewer.presentation.feeds

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.ui.widgets.FeedsScreenFormTextField
import com.perfomax.dataviewer.data.network.provideFeedApi
import com.perfomax.dataviewer.data.repository.FeedRepositoryImpl
import com.perfomax.dataviewer.domain.repository.FeedRepository
import com.perfomax.dataviewer.domain.usecases.GetFeedDataUseCase
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.presentation.home.HomeScreen
import com.perfomax.dataviewer.presentation.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FeedsScreen(
    uiState: FeedsContract.State,
    onFeedFieldChange: (String) -> Unit,
    onAddFeedClick: () -> Unit,
) {

    val articles = remember { mutableStateOf(emptyList<String>()) }
    val rememberScope = rememberCoroutineScope()
    val feedRepository: FeedRepository = FeedRepositoryImpl (
        feedApi = provideFeedApi(
            apiUrl = "https://feeds-mic.s1.citilink.ru/yandex_offer/msk_cl.xml"
        ),
        dispatcher = Dispatchers.IO
    )
    val feedUseCase = GetFeedDataUseCase(feedRepository)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .border(1.dp, Color.Red),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            FeedsScreenFormTextField(
                text = uiState.feedUrl,
                labelText = "Feed Url",
                isError = uiState.feedUrlError,
                onChange = onFeedFieldChange
            )
            Spacer(modifier = Modifier.width(10.dp))

            Button(
                modifier = Modifier
                    .width(200.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    rememberScope.launch {
                        articles.value = feedUseCase.execute()
//                    feedUseCase.execute()
                    }
                }
            ) {
                Text(text = "Загрузить новый фид", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(1.dp, Color.Red)
                .padding(15.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(articles.value) { element ->
                    Text(
                        modifier = Modifier.clickable {},
                        text = element
                    )
                }
            }
        }

    }








}

@Preview(showBackground = true)
@Composable
fun FeedsScreenPreview() {
    val feedsViewModel: FeedsViewModel = viewModel()
    val feedsUiState by feedsViewModel.uiState.collectAsStateWithLifecycle()
    val feedsEffects by feedsViewModel.effect.collectAsStateWithLifecycle()
    FeedsScreen (
        uiState = feedsUiState,
        onFeedFieldChange = { text -> feedsViewModel.intent(FeedsContract.Event.FeedUrlChangeEvent(text)) },
        onAddFeedClick = { feedsViewModel.intent(FeedsContract.Event.AddFeedClickEvent) }
    )

    DataViewerTheme {
//        FeedsScreen()
    }
}