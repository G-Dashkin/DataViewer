package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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

    onClickFeedElement: (String) -> Unit = {},
    onOpenChangeFeedDialog: (String) -> Unit = {},

    countElements: Int = 0,
    updateTime: String = "",
    loadTime: String = "",
    isClickableElement: Boolean = false,

    ) {
    Row(
        modifier = if (isClickableElement) Modifier
            .fillMaxWidth()
            .clickable { onClickFeedElement.invoke(feedName) }
        else Modifier.fillMaxWidth()
    ) {
        if (onRemoveBottom){
            Box(modifier = Modifier
                .padding(10.dp)
                .border(1.dp, Color.Gray)
            ) { Text(color = Color.Black, text = feedName) }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        top = 0.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                    ),
                    onClick = { onOpenChangeFeedDialog.invoke(feedName) }
                ) { Text(text = "Изменить",
                         color = MaterialTheme.colorScheme.onSecondary,
                         style = MaterialTheme.typography.titleMedium) }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        top = 0.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                    ),
                    onClick = { onRemove.invoke(feedName) }
                ) { Text(text = "Удалить",
                         color = MaterialTheme.colorScheme.onSecondary,
                         style = MaterialTheme.typography.titleMedium) }
            }
        } else {
            Column(modifier = Modifier
                .padding(start = padding10, top = padding10, end = padding10)
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


