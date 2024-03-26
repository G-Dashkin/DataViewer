package com.perfomax.dataviewer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.perfomax.dataviewer.presentation.menu.navigation.DataViewerNavHost
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataViewerTheme {
                DataViewerNavHost()
            }
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}



