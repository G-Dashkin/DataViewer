package com.perfomax.dataviewer.presentation.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.ui.widgets.InputDialogView
import com.perfomax.dataviewer.core.ui.widgets.ProjectItem
import com.perfomax.dataviewer.core.utils.removeElement

@Composable
fun ProjectsScreen(
    uiState: ProjectsContract.State,
    onProjectNameChange: (String) -> Unit,
    onCreateNewProjectClick: () -> Unit,
    onRemoveProjectClick: () -> Unit,
    onSelectRemovedProjectNameClick: (String) -> Unit,
    onClearProjectNameFieldClick: () -> Unit,
    onSelectProjectClick: (String) -> Unit
) {

    val openDialogCreateNewProject = remember { mutableStateOf(false) }
    val openDialogRemoveProject = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "----Выбранный проект----")
        Text(text = uiState.selectedProject)
        Text(text = "-----------------------------------------")
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = { openDialogCreateNewProject.value = true }
        ) { Text(text = "Создать новый проект", fontSize = 14.sp) }

        LazyColumn {
            items(uiState.projectsList.size) { projectIndex ->
                ProjectItem(
                    projectName = uiState.projectsList[projectIndex],
                    isChanged = uiState.selectedProject == uiState.projectsList[projectIndex],
                    onSelect = onSelectProjectClick,
                    onRemove = {
                        onSelectRemovedProjectNameClick.invoke(uiState.projectsList[projectIndex])
                        openDialogRemoveProject.value = true
                    }
                )
            }
        }

        InputDialogView(
            textValue = uiState.projectName,
            title = "Название нового проекта",
            addFieldValue = true,
            openDialog = openDialogCreateNewProject,
            onCancel = {
                onClearProjectNameFieldClick.invoke()
                openDialogCreateNewProject.value = false
            },
            onConfirm = {
                onCreateNewProjectClick.invoke()
                onClearProjectNameFieldClick.invoke()
                openDialogCreateNewProject.value = false
            },
            onFieldChange = onProjectNameChange
        )

        InputDialogView(
            textValue = uiState.projectName,
            title = "Удалить проект ${uiState.removedProject}",
            openDialog = openDialogRemoveProject,
            onCancel = { openDialogRemoveProject.value = false },
            onConfirm = {
                onRemoveProjectClick.invoke()
                openDialogRemoveProject.value = false
            },
            onFieldChange = onProjectNameChange
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsScreenPreview() {
    DataViewerTheme {
        ProjectsScreen(
            uiState = ProjectsContract.State(
                projectName = "",
                projectNameError = false,
                removedProject = "",
                projectsList = listOf("FirstProject", "SecondProject", "TriedProject"),
                selectedProject = "FirstProject"
            ),
            onProjectNameChange = { },
            onCreateNewProjectClick = { },
            onSelectRemovedProjectNameClick = { },
            onRemoveProjectClick = { },
            onClearProjectNameFieldClick = { },
            onSelectProjectClick = { }
        )
    }
}