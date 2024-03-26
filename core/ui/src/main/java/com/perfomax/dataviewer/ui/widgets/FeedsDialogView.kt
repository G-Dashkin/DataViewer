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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
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
fun FeedsDialogView(
    title: String = "",

    feedNameValue: String = "",
    onFeedNameFieldChangeValue:(String) -> Unit,

    feedElementNameValue: String = "",
    onFeedElementFieldChangeValue:(String) -> Unit,

    hasDateElement: Boolean = false,
    dateElement: String = "",
    useDateElement:(Boolean) -> Unit,

    openDialog: Boolean,
    onCancel:() -> Unit,
    onConfirm:() -> Unit,

) {
    if (openDialog){
        Dialog(
            onDismissRequest = {}
        ) {
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
                        modifier = Modifier.padding(8.dp),
                        value = feedNameValue,
                        onValueChange = onFeedNameFieldChangeValue,
                        label = {
                            Text(
                                color = Color.Black,
                                text = "Название фида"
                            )
                        }
                    )
                    //----------------------------------------
                    TextField(
                        modifier = Modifier.padding(8.dp),
                        value = feedElementNameValue,
                        onValueChange = onFeedElementFieldChangeValue,
                        label = {
                            Text(
                                color = Color.Black,
                                text = "Выбранный элемент (без угловых скобок <>)"
                            )
                        }
                    )

                    if (hasDateElement){
                        Text(
                             text = "Обнаруженные временные данные в фиде: $dateElement",
                             modifier = Modifier.padding(10.dp),
                             fontSize = 20.sp
                         )
                        Row {
                            Text(
                                text = "Использовать?",
                                modifier = Modifier.padding(10.dp),
                                fontSize = 20.sp
                            )
                            Checkbox(checked = false, onCheckedChange = useDateElement)

                        }
                    }


                    Row {
                        OutlinedButton(
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
                        ) { Text(text = "Отменить") }

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
                        ) { Text(text = "Подтвердить") }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedsDialogViewPreview() {
    DataViewerTheme {
        FeedsDialogView(
            title = "Добвление нововго фида",

            feedNameValue = "",
            onFeedNameFieldChangeValue = {  },

            feedElementNameValue = "",
            onFeedElementFieldChangeValue = { },

            hasDateElement = true,
            dateElement = "<yml_catalog date=\"2024-03-21 20:52\">",
            useDateElement = {},
            openDialog = true,
            onCancel = {},
            onConfirm = {}
        )
    }
}