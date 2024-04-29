package com.perfomax.dataviewer.ui.widgets

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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.perfomax.dataviewer.ui.theme.cornerShape10
import com.perfomax.dataviewer.ui.theme.fillMaxWidth05
import com.perfomax.dataviewer.ui.theme.height2
import com.perfomax.dataviewer.ui.theme.height40
import com.perfomax.dataviewer.ui.theme.height5
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.padding5
import com.perfomax.dataviewer.ui.theme.width5
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.ui.R

@Composable
fun FeedItemSettings(
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
            Box(
                modifier = Modifier.padding(start = padding5, top = padding15)
            ) {
                Text(color = Color.Black, text = feedName)
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                Button(modifier = Modifier.requiredHeight(height40),
                    shape = RoundedCornerShape(cornerShape10),
                    contentPadding = PaddingValues(
                        start = padding10,
                        top = zeroVal,
                        end = padding10,
                        bottom = zeroVal,
                    ),
                    onClick = { onOpenChangeFeedDialog.invoke(feedName) }
                ) { Text(text = stringResource(id = R.string.change),
                         color = MaterialTheme.colorScheme.onSecondary,
                         style = MaterialTheme.typography.titleMedium) }
                Spacer(modifier = Modifier.width(width5))
                Button(modifier = Modifier.requiredHeight(height40),
                    shape = RoundedCornerShape(cornerShape10),
                    contentPadding = PaddingValues(
                        start = padding10,
                        top = zeroVal,
                        end = padding10,
                        bottom = zeroVal,
                    ),
                    onClick = { onRemove.invoke(feedName) }
                ) {
                    Text(text = stringResource(id = R.string.delete),
                         color = MaterialTheme.colorScheme.onSecondary,
                         style = MaterialTheme.typography.titleMedium)
                }
            }

        } else {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding10, top = padding10, end = padding10),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth(fillMaxWidth05)
                    .padding(end = padding5),
                    horizontalAlignment = Alignment.End) {
                    Text(text = stringResource(id = R.string.feed_name_two))
                    Text(text = stringResource(id = R.string.elements_into_feed))
                    Text(text = stringResource(id = R.string.update_feed))
                    Text(text = stringResource(id = R.string.loaded_feed))
                }
                Column(modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center) {
                    Text(text = feedName)
                    Text(text = countElements.toString())
                    Text(text = updateTime)
                    Text(text = loadTime)
                }
            }
            }
        }
        Spacer(modifier = Modifier.height(height5))
        Divider (modifier = Modifier.fillMaxWidth()
                                    .height(height2),
                 color = Color.Black)
}


