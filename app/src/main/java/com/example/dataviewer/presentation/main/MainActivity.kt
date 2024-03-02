package com.example.dataviewer.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dataviewer.core.ui.theme.DataViewerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataViewerTheme {
                Surface {
                    val mainViewModel: MainViewModel = viewModel()
                    val state by mainViewModel.uiState.collectAsStateWithLifecycle()
                    MainScreen(
                        state = state,
                        onHomeScreen = mainViewModel::onHomeClick,
//                        onScanningScreen = mainViewModel::onScanningClick,
                        onProjectsScreen = mainViewModel::onProjectsClick,
                        onFeedsScreen = mainViewModel::onFeedsClick
                    )
                }
            }
        }
    }
}



