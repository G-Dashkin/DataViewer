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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.perfomax.dataviewer.ui.theme.cornerShape10
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding7
import com.perfomax.dataviewer.ui.theme.weight1
import com.perfomax.ui.R

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
    errorMessage: String = "",
    onUpdateProjectClick: () -> Unit
) {
    if (openDialog){
        Dialog(
            onDismissRequest = {}
        ) {
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
                    if (addFieldValue) {
                        TextField(
                            modifier = Modifier.padding(padding7),
                            value = textValue,
                            onValueChange = onFieldChange,
                            label = {
                                if (hasError) {
                                    Text(
                                        color = Color.Red,
                                        text = errorMessage)
                                }
                            }
                        )
                    }
                    Row {
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth()
                                               .padding(padding10)
                                               .weight(weight1),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = onCancel
                        ) {
                            Text(text = stringResource(id = R.string.cancel),
                                 style = MaterialTheme.typography.titleMedium)
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth()
                                               .padding(padding10)
                                               .weight(weight1),
                            shape = RoundedCornerShape(cornerShape10),
                            contentPadding = PaddingValues(padding10),
                            onClick = {
                                onConfirm.invoke()
                                onUpdateProjectClick.invoke()
                            },

                        ) {
                            Text(text = stringResource(id = R.string.confirm),
                                 style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}