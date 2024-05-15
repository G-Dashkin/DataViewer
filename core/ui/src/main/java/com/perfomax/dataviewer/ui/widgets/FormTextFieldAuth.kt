package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.perfomax.dataviewer.ui.theme.cornerShape10
import com.perfomax.dataviewer.ui.theme.height15
import com.perfomax.dataviewer.ui.theme.padding30

@Composable
fun FormTextFieldAuth(
    text: String,
    labelText: String,
    isError: Boolean,
    errorMessage: String,
    onChange: (value: String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth()
                           .defaultMinSize(minHeight = height15)
                           .background(color = MaterialTheme.colorScheme.background)
                           .padding(start = padding30, end = padding30),
        value = text,
        onValueChange = onChange,
        shape = RoundedCornerShape(cornerShape10),
        label = {
            if (isError) {
                Text(text = errorMessage, color = Color.Red)
            } else {
                Text(text = labelText)
            }
        },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}