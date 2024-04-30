package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.border1
import com.perfomax.dataviewer.ui.theme.cornerShape10
import com.perfomax.dataviewer.ui.theme.cornerShape22
import com.perfomax.dataviewer.ui.theme.height25
import com.perfomax.dataviewer.ui.theme.height40
import com.perfomax.dataviewer.ui.theme.padding5
import com.perfomax.dataviewer.ui.theme.weight1
import com.perfomax.ui.R

@Composable
fun FormTextFieldDefault(
    modifier: Modifier = Modifier,
    text: String,
    labelText: String,
    isError: Boolean,
    onChange: (value: String) -> Unit
) {
    BasicTextField(modifier = modifier.background(
        color = Color.Gray,
        shape = RoundedCornerShape(cornerShape22))
        .requiredHeight(height40),
        value = text,
        onValueChange = onChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.headlineMedium
            .merge(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textDecoration = TextDecoration.None),
        decorationBox = { innerTextField ->
            Row(modifier = modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(cornerShape10))
                .border(border1, color = Color.Gray, shape = RoundedCornerShape(cornerShape10)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(weight1)
                    .padding(start = padding5)
                    .height(height25)
                ) {
                    if (text.isEmpty()) {
                        Text(text = labelText,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
                        )
                    }
                    innerTextField()
                }
            }

        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultFormTextFieldPreview() {
    val textState = remember { mutableStateOf("") }
    val onTextChange = { text : String -> textState.value = text }
    DataViewerTheme {
        FormTextFieldDefault(
            modifier = Modifier.fillMaxWidth(),
            text = textState.value,
            onChange = onTextChange,
            labelText = stringResource(id = R.string.email),
            isError = false
        )
    }
}