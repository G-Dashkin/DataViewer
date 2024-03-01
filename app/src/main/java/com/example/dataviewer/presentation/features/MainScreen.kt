package com.example.dataviewer.presentation.features

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun MainScreen() {

    val articles = remember { mutableStateOf(emptyList<String>()) }

    val feedRepository: FeedRepository = FeedRepositoryImpl (
        feedApi = provideFeedApi(
            apiUrl = "https://feeds-mic.s1.citilink.ru/context/context_msk_cl.xml"
        ),
        dispatcher = Dispatchers.IO
    )

    //----------------------------------------------------------------------------------------------
//    val api = object : FeedApi {
//        override fun getData(): List<String> {
//            val mockFeed = TestFeed().mockFeed
//            val CITI = "https://feeds-mic.s1.citilink.ru/context/context_msk_cl.xml"
//            val PIZZA = "https://api2.kiparo.com/static/it_news.xml"
//
//            val arrayFeed = Parser.parsingToList(mockFeed)
//
//            return arrayFeed
//        }
//    }
//    val testRepository = FeedRepositoryImpl(api)
    val feedUseCase = GetFeedDataUseCase(feedRepository)
    //----------------------------------------------------------------------------------------------
    var someArray = ArrayList<String>()


//    CoroutineScope(Dispatchers.IO).launch {
//        feedArray.execute()
//    }

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
            Button(onClick = {}){ Text(text = "Загрузить фид")}
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(1.dp, Color.Red)
            .padding(15.dp)
        ) {
            Text(text = "Определить корневой элемент")
            Text(text = "Количество id элементов: ")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(1.dp, Color.Red)
            .padding(15.dp)
        ) {
            Text(text = "Структура id элемента")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(1.dp, Color.Red)
            .padding(15.dp)
        ) {

            LaunchedEffect(Unit) {
//                withContext(Dispatchers.IO){

                articles.value = feedUseCase.execute()
//                    val someArray2 = feedUseCase.execute()
//                someArray2.forEach {
//                    someArray.add(it)
//                }
//                    Log.d("MyLog", someArray.toString())

//                }

            }



            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(articles.value) {element ->
                    Log.d("MyLog", element)
                    Text(text = element)
                    
                }
            }
        }




    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    DataViewerTheme {
        MainScreen()
    }
}