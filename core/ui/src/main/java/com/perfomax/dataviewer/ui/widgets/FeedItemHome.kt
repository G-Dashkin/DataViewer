package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.border1
import com.perfomax.dataviewer.ui.theme.cornerShape15
import com.perfomax.dataviewer.ui.theme.fillMaxWidth05
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding5
import com.perfomax.dataviewer.ui.theme.space0
import com.perfomax.ui.R

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
        .background(Color(0xBE97ACD6), shape = RoundedCornerShape(cornerShape15))
        .border(border1, color = Color.Gray, shape = RoundedCornerShape(cornerShape15))
        .clip(shape = RoundedCornerShape(cornerShape15))
        .padding(top = padding5, bottom = padding5)
        .clickable { onClickFeedElement.invoke(feedName) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(fillMaxWidth05)
                               .padding(end = padding5),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(space0)
        ) {
            Text(text = stringResource(id = R.string.feed_name_two),
                 style = MaterialTheme.typography.bodyLarge.copy(
                 platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(text = stringResource(id = R.string.elements_into_feed),
                style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(text = stringResource(id = R.string.update_feed),
                 style = MaterialTheme.typography.bodyLarge.copy(
                 platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(text = stringResource(id = R.string.update_feed),
                style = MaterialTheme.typography.bodyLarge.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(space0)
        ) {
            Text(text = feedName,
                 style = MaterialTheme.typography.bodyLarge.copy(
                 platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(text = countElements.toString(),
                 style = MaterialTheme.typography.bodyLarge.copy(
                 platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(text = updateTime,
                 style = MaterialTheme.typography.bodyLarge.copy(
                 platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(text = loadTime,
                 style = MaterialTheme.typography.bodyLarge.copy(
                 platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
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