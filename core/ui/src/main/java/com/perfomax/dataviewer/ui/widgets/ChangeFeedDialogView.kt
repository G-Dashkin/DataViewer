package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape10
import com.perfomax.dataviewer.ui.theme.fillMaxWidth04
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.ui.R

@Composable
fun ChangeFeedDialogView(
    openDialog: Boolean,
    feedTitle: String,
    feedUrl: String,
    feedCountElement: String,
    onFeedTitleFieldChangeValue:(String) -> Unit,
    onFeedUrlFieldChangeValue:(String) -> Unit,
    onFeedCountElementFieldChangeValue:(String) -> Unit,
    onClose:() -> Unit,
    onSave:() -> Unit
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
                    TextField(
                        modifier = Modifier.padding(padding10),
                        value = feedUrl,
                        onValueChange = onFeedUrlFieldChangeValue,
                        label = {
                            Text(text = stringResource(id = R.string.feed_url))
                        }
                    )
                    TextField(
                        modifier = Modifier.padding(padding10),
                        value = feedTitle,
                        onValueChange = onFeedTitleFieldChangeValue,
                        label = {
                            Text(text = stringResource(id = R.string.feed_name))
                        }
                    )
                    TextField(
                        modifier = Modifier.padding(padding10),
                        value = feedCountElement,
                        onValueChange = onFeedCountElementFieldChangeValue,
                        label = {
                            Text(text = stringResource(id = R.string.main_element))
                        }
                    )
                    Row {
                        Button(
                            modifier = Modifier.fillMaxWidth(fillMaxWidth04)
                                               .padding(padding10),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = onClose,
                        ) {
                            Text(text = stringResource(id = R.string.close),
                                 color = MaterialTheme.colorScheme.onSecondary,
                                 style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth()
                                               .padding(padding10),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10,),
                            onClick = onSave,
                        ) {
                            Text(text = stringResource(id = R.string.save),
                                 color = MaterialTheme.colorScheme.onSecondary,
                                 style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeFeedDialogViewPreview() {
    DataViewerTheme {
        ChangeFeedDialogView(
            openDialog = true,
            feedTitle = "feedName",
            feedUrl = "www.feedUrl.com/feed",
            feedCountElement = "feedCountElement",
            onFeedTitleFieldChangeValue = {  },
            onFeedUrlFieldChangeValue = {  },
            onFeedCountElementFieldChangeValue = {  },
            onClose = {  },
            onSave = {  }
        )
    }
}