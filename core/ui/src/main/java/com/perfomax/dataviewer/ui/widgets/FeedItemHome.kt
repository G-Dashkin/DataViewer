package com.perfomax.dataviewer.ui.widgets

import android.provider.ContactsContract.Contacts.Data
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.bodyLargeBold
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding5

@Composable
fun FeedItemHome(
    feedName: String,
    onClickFeedElement: (String) -> Unit,
    countElements: Int,
    updateTime: String = "",
    loadTime: String,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xBE97ACD6), shape = RoundedCornerShape(15.dp))
        .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
        .clip(shape = RoundedCornerShape(15.dp))
        .padding(top = 5.dp, bottom = 5.dp)
        .clickable { onClickFeedElement.invoke(feedName) })
    {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = padding5),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Text(text = "Название фида:", style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
            Text(text = "Элементы в фиде:", style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
            Text(text = "Обновление из фида:", style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
            Text(text = "Загрузка фида:", style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Text(text = feedName, style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
            Text(text = countElements.toString(), style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
            Text(text = updateTime, style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
            Text(text = loadTime, style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)))
        }
    }
    Spacer(modifier = Modifier.height(padding10))
}



@Preview(showBackground = true)
@Composable
fun FeedItemHomePreview() {
    DataViewerTheme {
        FeedItemHome(
            feedName = "myFeed",
            countElements = 99999,
            updateTime = "2024-04-14 24:05",
            loadTime = "2024-04-06 11:42",
            onClickFeedElement = {  }
        )
    }
}