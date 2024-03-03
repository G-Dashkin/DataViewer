package com.example.dataviewer.presentation

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.data.network.provideFeedApi
import com.example.dataviewer.data.repository.FeedRepositoryImpl
import com.example.dataviewer.domain.repository.FeedRepository
import com.example.dataviewer.domain.usecases.GetFeedDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TestScreen() {

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
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Gray),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                rememberScope.launch {
                    articles.value = feedUseCase.execute()
//                    feedUseCase.execute()
                }
            }){ Text(text = "Загрузить фид")}
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .border(1.dp, Color.Red)
            .padding(15.dp)
        ) {



            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(articles.value) {element ->
                    Text(modifier = Modifier.clickable {},
                        text = element
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    DataViewerTheme {
        TestScreen()
    }
}
