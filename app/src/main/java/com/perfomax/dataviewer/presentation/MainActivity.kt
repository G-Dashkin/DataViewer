package com.perfomax.dataviewer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.navigaion.DataViewerNavHost
import com.perfomax.dataviewer.domain.utill.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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



