package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.perfomax.ui.R
import com.perfomax.dataviewer.ui.theme.DataViewerTheme

@Composable
fun FeedsScreenFormTextField(
    modifier: Modifier = Modifier,
    text: String,
    labelText: String,
    isError: Boolean,
    onChange: (value: String) -> Unit
) {
    BasicTextField(
        modifier = modifier
            .background(
            color = Color.Gray,
            shape = RoundedCornerShape(22.dp))
            .requiredHeight(40.dp),
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
                shape = RoundedCornerShape(10.dp))
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f)
                    .padding(start = 5.dp)
                    .height(25.dp)
                ) {
                    if (text.isEmpty()) {
                        Text(text = labelText,
                            style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        )
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
fun FeedsScreenFormTextFieldPreview() {
    val textState = remember { mutableStateOf("") }
    val onTextChange = { text : String -> textState.value = text }
    DataViewerTheme {
        DefaultFormTextField(
            modifier = Modifier.fillMaxWidth(),
            text = textState.value,
            onChange = onTextChange,
            labelText = stringResource(id = R.string.email),
            isError = false,
            icon = painterResource(id = R.drawable.ic_bottom_menu_search),
        )
    }
}