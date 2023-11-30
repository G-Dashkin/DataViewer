package com.example.dataviewer.core.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dataviewer.R
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.core.ui.theme.borderRadius8
import com.example.dataviewer.core.ui.theme.height56
import com.example.dataviewer.core.ui.theme.itemSize24
import com.example.dataviewer.core.ui.theme.padding5
import com.example.dataviewer.core.ui.theme.shape8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultFormTextField(
    modifier: Modifier = Modifier,
    text: String,
    labelText: String,
    isError: Boolean,
    onChange: (value: String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(shape8))
            .background(color = White)
            .height(height56),
        value = text,
        onValueChange = onChange,
        shape = RoundedCornerShape(borderRadius8),
        label = {
            Text(modifier = Modifier.padding(top = padding5),
                text = labelText, style = MaterialTheme.typography.labelMedium, color = Gray)
        },
        textStyle = MaterialTheme.typography.labelMedium,
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
            labelText = "labelText",
            isError = false
        )
    }
}