package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape26
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.height60
import com.perfomax.dataviewer.ui.theme.size24
import com.perfomax.dataviewer.ui.theme.width300
import com.perfomax.ui.R

@Composable
fun DefaultFormTextField(
    modifier: Modifier = Modifier,
    text: String,
    labelText: String,
    isError: Boolean,
    icon: Painter,
    onChange: (value: String) -> Unit
) {
    TextField(
        modifier = Modifier.width(width300)
                           .height(height60)
                           .clip(shape = RoundedCornerShape(cornerShape26))
                           .background(color = MaterialTheme.colorScheme.background),
        value = text,
        leadingIcon = {
            Image(modifier = Modifier.size(size24),
                  painter = icon,
                  contentDescription = text
            )
        },
        onValueChange = onChange,
        shape = RoundedCornerShape(cornerShape8),
        label = {
            Text(modifier = Modifier,
                text = labelText, style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface)
        },
        textStyle = MaterialTheme.typography.labelMedium
                    .merge(color = MaterialTheme.colorScheme.onBackground,
                           textDecoration = TextDecoration.None),
        isError = isError
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultFormTextFieldPreview() {
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