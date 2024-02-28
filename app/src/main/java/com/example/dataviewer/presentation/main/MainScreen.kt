package com.example.dataviewer.presentation.main

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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.core.ui.widgets.DefaultFormTextField

@Composable
fun MainScreen() {
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
//            LazyColumn(content = )
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