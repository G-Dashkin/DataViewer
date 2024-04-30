package com.perfomax.dataviewer.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.fillMaxWidth07
import com.perfomax.dataviewer.ui.theme.height2
import com.perfomax.dataviewer.ui.theme.height40
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding7
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.ui.R

@Composable
fun ItemProject(
    projectName: String,
    isChanged: Boolean,
    onSelect: (String) -> Unit,
    onRemove: (String) -> Unit,
    onUpdateTitleClick: () -> Unit
) {
    val selectedProject = if (isChanged) Modifier.background(Color.Gray, shape =  RoundedCornerShape(cornerShape8))
                          else Modifier.background(Color.White)
    val selectedProjectTitle = if (isChanged) Color.White else Color.Black

    Row(modifier = selectedProject.fillMaxWidth()
                                  .requiredHeight(height40)
    ) {
        Box(modifier = Modifier.fillMaxWidth(fillMaxWidth07)
                               .padding(padding10)
                               .clickable {
                                   onSelect.invoke(projectName)
                                   onUpdateTitleClick.invoke()
                                }
        ) {
            Text(
                color = selectedProjectTitle,
                text = projectName,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Box(modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth()
                                   .height(height50)
                                   .requiredHeight(height40),
                shape = RoundedCornerShape(cornerShape8),
                contentPadding = PaddingValues(zeroVal),
                onClick = { onRemove.invoke(projectName) }
            ) {
                Text(text = stringResource(id = R.string.delete),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleMedium)
            }
        }
    }
    Divider (
        modifier = Modifier.fillMaxWidth()
                           .padding(start = padding7, end = padding7) 
                           .height(height2),
        color = Color.Gray
    )
}