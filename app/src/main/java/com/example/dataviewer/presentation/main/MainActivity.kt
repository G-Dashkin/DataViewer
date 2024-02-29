package com.example.dataviewer.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.data.network.api.FeedApi
import com.example.dataviewer.data.network.provideFeedApi
import com.example.dataviewer.data.repository.FeedRepositoryImpl
import com.example.dataviewer.domain.repository.FeedRepository
import kotlinx.coroutines.Dispatchers

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
