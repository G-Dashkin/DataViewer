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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.padding10

@Composable
fun HomeScreenFeedDialogView(
    title: String = "",
    openDialog: Boolean,
    feedFindElementValue: String = "",
    onFeedFindElementChangeValue: (String) -> Unit,
    onFindSelectedElement: () -> Unit,
    onUpdateFeed:() -> Unit,
    onClose:() -> Unit
) {
    if (openDialog) {
        Dialog(onDismissRequest = {}) {

            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Column(
                    Modifier.background(Color.White)
                ) {
                    Text(
                        text = "Фид: $title",
                        modifier = Modifier.padding(padding10),
                        fontSize = 20.sp
                    )
                    Row {
                        TextField(
                            modifier = Modifier
                                .padding(start = padding10, end = padding10)
                                .width(200.dp),
                            value = feedFindElementValue,
                            onValueChange = onFeedFindElementChangeValue,
                            label = {
                                Text(
                                    color = Color.Red,
                                    text = "Элемент поиска в фиде"
                                )
                            }
                        )
                        Button(
                            modifier = Modifier.padding(end = padding10).height(60.dp),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(padding10),
                            onClick = onFindSelectedElement,
                        ) { Text(
                            text = "Найти",
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium
                        ) }
                    }
                    Row {
                        Button(
                            modifier = Modifier.padding(10.dp).fillMaxWidth(0.4f),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(padding10,),
                            onClick = onClose,
                        ) { Text(
                            text = "Закрыть",
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium
                        ) }
                        Button(
                            modifier = Modifier.padding(10.dp).fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(padding10,),
                            onClick = onUpdateFeed,
                        ) { Text(
                            text = "Обновить фид",
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
//        HomeScreenFeedDialogView(
//            title = "Добвление нововго фида",
//
//            feedNameValue = "",
//            onFeedNameFieldChangeValue = {  },
//
//            feedElementNameValue = "",
//            onFeedElementFieldChangeValue = { },
//
//            selectDateElement = {},
//            selectedFeedDateElement = "",
//
//            dateElement = "<yml_catalog date=\"2024-03-21 20:52\">",
//            useDateElement = {},
//            openDialog = true,
//            onCancel = {},
//            onConfirm = {}
//        )
    }
}