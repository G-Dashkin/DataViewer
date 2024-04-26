package com.perfomax.dataviewer.ui.widgets

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.perfomax.dataviewer.ui.theme.shape8
import com.perfomax.dataviewer.ui.theme.zeroVal

@Composable
fun ProjectItem(
    modifier: Modifier = Modifier,
    projectName: String,
    isChanged: Boolean,
    onSelect: (String) -> Unit,
    onRemove: (String) -> Unit,
    onUpdateTitleClick: () -> Unit
) {
    val selectedProject = if (isChanged) Modifier.background(Color.Gray, shape =  RoundedCornerShape(8.dp))
                          else Modifier.background(Color.White)
    val selectedProjectTitle = if (isChanged) Color.White else Color.Black

    Row(modifier = selectedProject.fillMaxWidth().requiredHeight(40.dp)
    ) {
        Box(modifier = Modifier
            .clickable {
                onSelect.invoke(projectName)
                onUpdateTitleClick.invoke()
            }
            .padding(10.dp)
            .fillMaxWidth(0.7f)
        ) {
                Text(
                    color = selectedProjectTitle,
                    text = projectName,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        Box(modifier = Modifier
            .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth()
                                   .height(50.dp)
                                   .requiredHeight(40.dp),
                shape = RoundedCornerShape(shape8),
                contentPadding = PaddingValues(zeroVal),
                onClick = {onRemove.invoke(projectName)}
            ) {
                Text(text = "Удалить",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleMedium)
            }
        }
    }
    Divider (
        modifier = Modifier.height(1.dp).fillMaxWidth().padding(start = 7.dp, end = 7.dp),
        color = Color.Gray
    )
}