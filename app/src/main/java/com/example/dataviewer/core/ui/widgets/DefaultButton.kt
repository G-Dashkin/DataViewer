package com.example.dataviewer.core.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.core.ui.theme.borderRadius16
import com.example.dataviewer.core.ui.theme.height40

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    height: Dp = height40,
    title: String,
    color: Color = MaterialTheme.colorScheme.secondary,
    textColor : Color = MaterialTheme.colorScheme.onSecondary,
    onClick: () -> Unit,
    onClickState: () -> Unit = { },
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = {
            onClick.invoke()
            onClickState.invoke()
            },
        shape = RoundedCornerShape(borderRadius16),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            modifier = Modifier.wrapContentSize(unbounded = true),
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            ),
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultButtonPreview() {
    DataViewerTheme {
        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            title = "",
            onClick = {},
            onClickState = {}
        )
    }
}


