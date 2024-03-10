package com.perfomax.dataviewer.presentation.projects

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.presentation.home.HomeScreen
import com.perfomax.dataviewer.presentation.home.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun ProjectsScreen(
    uiState: ProjectsContract.State,
    onTextChange: (String) -> Unit,
    onTestClick: () -> Unit,
) {

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
//                    rememberScope.launch {
//                        articles.value = feedUseCase.execute()
////                    feedUseCase.execute()
//                    }
            }
        ) {
            Text(text = "Создать новый проект", fontSize = 14.sp)
        }
        Text(text = "Список проектов", fontSize = 14.sp)

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp)
//                .border(1.dp, Color.Red),
//            verticalAlignment = Alignment.Top,
//            horizontalArrangement = Arrangement.Center
//        ) {
//
//        }
    }

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "ProjectsScreen", fontSize = 22.sp)
//    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsScreenPreview() {
    DataViewerTheme {
        val projectsViewModel: ProjectsViewModel = viewModel()
        val projectsUiState by projectsViewModel.uiState.collectAsStateWithLifecycle()
        val projectsEffects by projectsViewModel.effect.collectAsStateWithLifecycle()
        ProjectsScreen(
            uiState = projectsUiState,
            onTextChange = { text -> projectsViewModel.intent(ProjectsContract.Event.TextChangeEvent(text)) },
            onTestClick = { projectsViewModel.intent(ProjectsContract.Event.ClickEvent) }
        )
    }
}