package com.perfomax.dataviewer.presentation.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.ui.widgets.InputDialogView

@Composable
fun ProjectsScreen(
    uiState: ProjectsContract.State,
    onProjectNameChange: (String) -> Unit,
    onCreateNewProjectClick: () -> Unit,
    onGetProjectClick: () -> Unit,
    onClearProjectNameClick: () -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value){
        InputDialogView(
            uiState = uiState,
            onCancel = {
                onClearProjectNameClick.invoke()
                openDialog.value = false
            },
            onConfirm = {
                onCreateNewProjectClick.invoke()
                onClearProjectNameClick.invoke()
                openDialog.value = false
            },
            onProjectNameChange = onProjectNameChange
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                openDialog.value = true
            }
        ) {
            Text(text = "Создать новый проект", fontSize = 14.sp)
        }
        Text(text = "Список проектов", fontSize = 14.sp)
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = onGetProjectClick
        ) {
            Text(text = "Получить проект из DataBase", fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsScreenPreview() {
    DataViewerTheme {
        val projectsViewModel: ProjectsViewModel = viewModel()
        val projectsUiState by projectsViewModel.uiState.collectAsStateWithLifecycle()
        val projectsEffects by projectsViewModel.effect.collectAsStateWithLifecycle()
//        ProjectsScreen(
////            uiState = projectsUiState,
////            onTextChange = { text -> projectsViewModel.intent(ProjectsContract.Event.TextChangeEvent(text)) },
////            onTestClick = { projectsViewModel.intent(ProjectsContract.Event.ClickEvent) }
//        )
    }
}