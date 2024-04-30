package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape10
import com.perfomax.dataviewer.ui.theme.fillMaxWidth04
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.ui.R

@Composable
fun DialogViewHomeScreenFeed(
    feedTitle: String,
    feedUrl: String,
    openDialog: Boolean,
    onFindElementsInFeed: (String) -> Unit,
    onUpdateFeed:() -> Unit,
    onClose:() -> Unit
) {
    if (openDialog) {
        Dialog(onDismissRequest = {}) {
            Card(
                shape = RoundedCornerShape(cornerShape10),
                modifier = Modifier.padding(padding10)
            ) {
                Column(
                    Modifier.background(Color.White)
                ) {
                    Text(text = "${stringResource(id = R.string.feed)} $feedTitle",
                         modifier = Modifier.padding(padding10),
                         fontSize = 20.sp)
                    Row {
                        Button(
                            modifier = Modifier.fillMaxWidth()
                                               .padding(padding10),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = {onFindElementsInFeed.invoke(feedUrl)},
                        ) {
                            Text(text = stringResource(id = R.string.feed_search_element),
                                 color = MaterialTheme.colorScheme.onSecondary,
                                 style = MaterialTheme.typography.titleMedium)
                        }
                    }
                    Row {
                        Button(
                            modifier = Modifier.fillMaxWidth(fillMaxWidth04)
                                               .padding(padding10),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = onClose
                        ) { Text(
                            text = stringResource(id = R.string.close),
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium
                        ) }
                        Button(
                            modifier = Modifier.fillMaxWidth()
                                               .padding(padding10),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = onUpdateFeed,
                        ) { Text(
                            text = stringResource(id = R.string.feed_update),
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium
                        ) }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenFeedDialogViewPreview() {
    DataViewerTheme {
        DialogViewHomeScreenFeed(
            feedTitle = "MyFeed",
            feedUrl = "www.feed.com",
            onFindElementsInFeed = {  },
            openDialog = true,
            onUpdateFeed = {  },
            onClose = {  }
        )
    }
}