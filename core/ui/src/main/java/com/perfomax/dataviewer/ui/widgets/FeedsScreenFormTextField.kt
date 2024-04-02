package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
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
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onChange,
//        textStyle  =
        label = {
            Text(modifier = Modifier,
                fontSize = 12.sp,
                text = labelText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface)
        },
        textStyle = MaterialTheme.typography.labelMedium
            .merge(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textDecoration = TextDecoration.None),
        isError = isError
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