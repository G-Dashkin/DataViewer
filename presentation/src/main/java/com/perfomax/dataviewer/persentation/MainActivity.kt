package com.perfomax.dataviewer.persentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.navigaion.DataViewerNavHost
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



