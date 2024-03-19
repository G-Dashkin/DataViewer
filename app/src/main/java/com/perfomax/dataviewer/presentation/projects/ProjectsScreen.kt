package com.perfomax.dataviewer.presentation.projects

import android.util.Log
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
    onSelectProjectClick: (String) -> Unit,
    onOpenDialogCreateClick: () -> Unit,
    onCloseDialogCreateClick: () -> Unit,
    onRemoveProjectClick: () -> Unit,
    onSelectRemovedProjectNameClick: (String) -> Unit,
    onCloseDialogRemoveClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = onOpenDialogCreateClick
        ) { Text(text = "Создать новый проект", fontSize = 14.sp) }

        LazyColumn {
            items(uiState.projectsList.size) { projectIndex ->
                ProjectItem(
                    projectName = uiState.projectsList[projectIndex],
                    isChanged = uiState.selectedProject == uiState.projectsList[projectIndex],
                    onSelect = onSelectProjectClick,
                    onRemove = onSelectRemovedProjectNameClick
                )
            }
        }
        InputDialogView(
            textValue = uiState.projectName,
            title = "Название нового проекта",
            addFieldValue = true,
            openDialog = uiState.openDialogCreateNewProject,
            onCancel = onCloseDialogCreateClick,
            onConfirm = onCreateNewProjectClick,
            onFieldChange = onProjectNameChange,
            hasError = uiState.projectNameError,
            errorMessage = uiState.errorMessage
        )

        InputDialogView(
            textValue = uiState.projectName,
            title = "Удалить проект ${uiState.removedProject}",
            openDialog = uiState.openDialogRemoveProject,
            onCancel = onCloseDialogRemoveClick,
            onConfirm = onRemoveProjectClick,
            onFieldChange = onProjectNameChange,
            errorMessage = uiState.errorMessage
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
                errorMessage = "",
                removedProject = "",
                projectsList = listOf("FirstProject", "SecondProject", "TriedProject"),
                selectedProject = "FirstProject",
                openDialogCreateNewProject = false,
                openDialogRemoveProject = false
            ),
            onProjectNameChange = { },
            onCreateNewProjectClick = { },
            onRemoveProjectClick = { },
            onSelectProjectClick = { },
            onOpenDialogCreateClick = { },
            onCloseDialogCreateClick = { },
            onSelectRemovedProjectNameClick = { },
            onCloseDialogRemoveClick = { }
        )
    }
}