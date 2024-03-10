package com.perfomax.dataviewer.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.ui.widgets.DefaultFormTextField

@Composable
fun HomeScreen(
    uiState: HomeContract.State,
    onTextChange: (String) -> Unit,
    onTestClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().border(1.dp, Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(150.dp))
        Text(text = "HomeScreen", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "TestMVI", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(32.dp))
        DefaultFormTextField(
            text = uiState.text,
            labelText = "TestText",
            isError = uiState.textError,
            onChange = onTextChange,
            icon = painterResource(id = R.drawable.ic_bottom_menu_search)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier.width(200.dp).padding(bottom = 20.dp),
            onClick = onTestClick
        ) {
            Text(text = "TestClick", fontSize = 22.sp)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DataViewerTheme {
        val homeViewModel: HomeViewModel = viewModel()
        val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
        val homeEffects by homeViewModel.effect.collectAsStateWithLifecycle()
        HomeScreen (
            uiState = homeUiState,
            onTextChange = { text -> homeViewModel.intent(HomeContract.Event.TextChangeEvent(text)) },
            onTestClick = { homeViewModel.intent(HomeContract.Event.ClickEvent) }
        )
    }
}