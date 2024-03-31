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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.perfomax.dataviewer.ui.theme.DataViewerTheme

@Composable
fun AddFeedDialogView(
    title: String = "",

    feedNameValue: String = "",
    onFeedNameFieldChangeValue:(String) -> Unit,

    feedElementNameValue: String = "",
    onFeedElementFieldChangeValue:(String) -> Unit,

    dateElement: String = "",
    useDateElement:(Boolean) -> Unit,

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
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Column(
                    Modifier.background(Color.White)
                ) {

                    Text(
                        text = title,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp
                    )
                    //----------------------------------------
                    TextField(
                        modifier = Modifier.padding(10.dp),
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
                                    text = "Название фида"
                                )
                            }
                        }
                    )
                    //----------------------------------------
                    TextField(
                        modifier = Modifier.padding(10.dp),
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
                                    text = "Выбранный элемент (без угловых скобок <>)"
                                )
                            }

                        }
                    )

                    if (selectedFeedDateElement.isEmpty()) {
                        Text(modifier = Modifier.padding(10.dp),
                            text = "Определить элемент даты и времени обновления в фиде",
                            style = MaterialTheme.typography.titleMedium)
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(
                                start = 10.dp,
                                top = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp,
                            ),
                            onClick = selectDateElement
                            ,
                        ) { Text(text = "Определить элемент",
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium) }
                    } else {
                        Text(modifier = Modifier.padding(10.dp),
                            text = "Время обновления из фида: ${
                                selectedFeedDateElement.split("=\"")[1].split("\"")[0]
                            }",
                            style = MaterialTheme.typography.titleMedium)
                    }

                    Row {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .weight(1F),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(
                                start = 10.dp,
                                top = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp,
                            ),
                            onClick = onCancel
                        ) { Text(
                            text = "Отменить",
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium
                        ) }

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .weight(1F),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(
                                start = 10.dp,
                                top = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp,
                            ),
                            onClick = onConfirm,
                        ) { Text(
                            text = "Подтвердить",
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
        AddFeedDialogView(
            title = "Добвление нововго фида",

            feedNameValue = "",
            onFeedNameFieldChangeValue = {  },

            feedElementNameValue = "",
            onFeedElementFieldChangeValue = { },

            selectDateElement = {},
            selectedFeedDateElement = "",

            dateElement = "<yml_catalog date=\"2024-03-21 20:52\">",
            useDateElement = {},
            openDialog = true,
            onCancel = {},
            onConfirm = {}
        )
    }
}