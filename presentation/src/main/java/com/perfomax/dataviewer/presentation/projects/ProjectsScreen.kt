package com.perfomax.dataviewer.presentation.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.height5
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.widgets.DialogViewCreateNewProject
import com.perfomax.dataviewer.ui.widgets.DialogViewDefault
import com.perfomax.dataviewer.ui.widgets.ItemProject
import com.perfomax.ui.R

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
    onCloseDialogRemoveClick: () -> Unit,
    onUpdateProjectClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
                           .padding(padding15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(cornerShape8),
            onClick = onOpenDialogCreateClick
        ) {
            Text(text = stringResource(id = R.string.create_new_project),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(padding10))
        LazyColumn {
            items(uiState.projectsList.size) { projectIndex ->
                ItemProject(
                    projectName = uiState.projectsList[projectIndex],
                    isChanged = uiState.selectedProject == uiState.projectsList[projectIndex],
                    onSelect = onSelectProjectClick,
                    onRemove = onSelectRemovedProjectNameClick,
                    onUpdateTitleClick = onUpdateProjectClick
                )
                Spacer(modifier = Modifier.height(height5))
            }
        }

        // DialogView for create new project
        DialogViewCreateNewProject(
            textValue = uiState.projectName,
            title = stringResource(id = R.string.name_new_project),
            addFieldValue = true,
            openDialog = uiState.openDialogCreateNewProject,
            onCancel = onCloseDialogCreateClick,
            onConfirm = onCreateNewProjectClick,
            onFieldChange = onProjectNameChange,
            hasError = uiState.projectNameError,
            errorMessage = uiState.errorMessage,
            onUpdateProjectClick = onUpdateProjectClick
        )

        // DialogView for delete project
        DialogViewDefault(
            textValue = uiState.projectName,
            title = stringResource(id = R.string.delete_project) + " ${uiState.removedProject}?",
            openDialog = uiState.openDialogRemoveProject,
            onCancel = onCloseDialogRemoveClick,
            onConfirm = onRemoveProjectClick,
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
            onCloseDialogRemoveClick = { },
            onUpdateProjectClick = { }
        )
    }
}