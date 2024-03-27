package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding15

@Composable
fun FeedItem(
    modifier: Modifier = Modifier,
    feedName: String,
    onRemove: (String) -> Unit = {},
    onRemoveBottom: Boolean = false,
    countElements: Int = 0,
    updateTime: String = "",
    loadTime: String = ""
) {
    Row(modifier = Modifier.fillMaxWidth()
    ) {
        if (onRemoveBottom){
            Box(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.7f)
                .border(1.dp, Color.Gray)
            ) { Text(color = Color.Black, text = feedName) }
            Box(modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Red)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        top = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp,
                    ),
                    onClick = { onRemove.invoke(feedName) }
                ) { Text(text = "Удалить") }
            }
        } else {
            Column(modifier = Modifier
                .padding(start = padding10, top = padding10, end = padding10)
//                .border(1.dp, Color.Red),
                ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(color = Color.Black, text = feedName)
                    Text(text = "Количество элементов в фиде: $countElements")
                }
                Text(text = "Дата обновления из фида: $updateTime")
                Text(text = "Дата загрузки фида: $loadTime")
            }
            }
        }
        Divider (
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(),
        color = Color.Black
    )
}


