package com.example.dataviewer.presentation.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.dataviewer.core.ui.theme.DataViewerTheme

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataViewerTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}
