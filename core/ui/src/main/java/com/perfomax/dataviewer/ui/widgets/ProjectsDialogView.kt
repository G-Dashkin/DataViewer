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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ProjectsDialogView(
    textValue: String,
    title: String = "",
    addFieldValue: Boolean = false,
    openDialog: Boolean,
    onCancel:() -> Unit,
    onConfirm:() -> Unit,
    onFieldChange:(String) -> Unit,
    hasError: Boolean = false,
    errorMessage: String = ""
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
                    if (addFieldValue) {
                        TextField(
                            modifier = Modifier.padding(8.dp),
                            value = textValue,
                            onValueChange = onFieldChange,
                            label = {
                                if (hasError) {
                                    Text(
                                        color = Color.Red,
                                        text = errorMessage
                                    )
                                }
                            }
                        )
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