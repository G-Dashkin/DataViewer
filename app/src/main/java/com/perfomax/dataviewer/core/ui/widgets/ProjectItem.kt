package com.perfomax.dataviewer.core.ui.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProjectItem(
    modifier: Modifier = Modifier,
    projectName: String,
    isChanged: Boolean,
    onSelect: (String) -> Unit,
    onRemove: (String) -> Unit
) {
    val selectedProject = if (isChanged) Modifier.background(Color.Gray) else Modifier.background(Color.White)
    Row(modifier = selectedProject
        .border(1.dp, Color.Blue)
        .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .clickable { onSelect.invoke(projectName) }
            .padding(10.dp)
            .fillMaxWidth(0.7f)
            .border(1.dp, Color.Red)
        ) {
                Text(color = Color.Black, text = projectName)
            }
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Green)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    top = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp,
                ),
                onClick = {onRemove.invoke(projectName)}
            ) {
                Text(text = "Удалить")
            }
        }
    }
}