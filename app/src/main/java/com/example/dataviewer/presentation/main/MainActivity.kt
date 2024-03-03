package com.example.dataviewer.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.presentation.bottom_menu.DataViewerNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataViewerTheme {
                DataViewerNavHost()

//                Surface {
//                    val mainViewModel: MainViewModel = viewModel()
//                    val state by mainViewModel.uiState.collectAsStateWithLifecycle()
//                    MainScreen(
//                        state = state,
//                        onHomeScreen = mainViewModel::onHomeClick,
////                        onScanningScreen = mainViewModel::onScanningClick,
//                        onProjectsScreen = mainViewModel::onProjectsClick,
//                        onFeedsScreen = mainViewModel::onFeedsClick
//                    )
//                }
            }
        }

    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}



