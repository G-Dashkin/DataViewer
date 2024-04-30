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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape10
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.ui.R

@Composable
fun DialogViewAddFeed(
    title: String = "",
    feedNameValue: String = "",
    onFeedNameFieldChangeValue:(String) -> Unit,
    feedElementNameValue: String = "",
    onFeedElementFieldChangeValue:(String) -> Unit,
    selectDateElement: () -> Unit,
    selectedFeedDateElement: String,
    openDialog: Boolean,
    onCancel:() -> Unit,
    onConfirm:() -> Unit,
    hasFeedNameError: Boolean = false,
    errorFeedNameMessage: String = "",
    hasUrlFeedError: Boolean = false,
    errorUrlFeedMessage: String = ""
) {
    if (openDialog){
        Dialog(onDismissRequest = {}) {
            Card(
                shape = RoundedCornerShape(cornerShape10),
                modifier = Modifier.padding(padding10)
            ) {
                Column(
                    Modifier.background(Color.White)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(padding10),
                        fontSize = 20.sp
                    )
                    TextField(
                        modifier = Modifier.padding(padding10),
                        value = feedNameValue,
                        onValueChange = onFeedNameFieldChangeValue,
                        label = {
                            if (hasFeedNameError) {
                                Text(
                                    color = Color.Red,
                                    text = errorFeedNameMessage
                                )
                            } else {
                                Text(
                                    color = Color.Black,
                                    text = stringResource(id = R.string.feed_name)
                                )
                            }
                        }
                    )
                    TextField(
                        modifier = Modifier.padding(padding10),
                        value = feedElementNameValue,
                        onValueChange = onFeedElementFieldChangeValue,
                        label = {
                            if (hasUrlFeedError) {
                                Text(
                                    color = Color.Red,
                                    text = errorUrlFeedMessage
                                )
                            } else {
                                Text(
                                    color = Color.Black,
                                    text = stringResource(id = R.string.feed_element)
                                )
                            }

                        }
                    )
                    if (selectedFeedDateElement.isEmpty()) {
                        Text(modifier = Modifier.padding(padding10),
                            text = stringResource(id = R.string.element_date_and_time),
                            style = MaterialTheme.typography.titleMedium)
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding10),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = selectDateElement
                        ) {
                            Text(text = stringResource(id = R.string.define_element),
                                 color = MaterialTheme.colorScheme.onSecondary,
                                 style = MaterialTheme.typography.titleMedium)
                        }
                    } else {
                        Text(modifier = Modifier.padding(padding10),
                            text = stringResource(id = R.string.feed_update_time) + "\n${
                                selectedFeedDateElement.split("=\"")[1].split("\"")[0]
                            }",
                            style = MaterialTheme.typography.titleMedium)
                    }

                    Row {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding10)
                                .weight(1F),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = onCancel
                        ) { Text(
                            text = stringResource(id = R.string.cancel),
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium
                        ) }

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding10)
                                .weight(1F),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = onConfirm,
                        ) { Text(
                            text = stringResource(id = R.string.confirm),
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
fun AddFeedDialogViewPreview() {
    DataViewerTheme {
        DialogViewAddFeed(
            title = "Добвление нововго фида",
            feedNameValue = "",
            onFeedNameFieldChangeValue = {  },
            feedElementNameValue = "",
            onFeedElementFieldChangeValue = { },
            selectDateElement = {},
            selectedFeedDateElement = "",
            openDialog = true,
            onCancel = {},
            onConfirm = {}
        )
    }
}