package com.perfomax.dataviewer.presentation.start

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme

@Composable
fun StartedScreen(
    onStartClicked: () -> Unit
) {

    Handler(Looper.getMainLooper()).postDelayed({
        onStartClicked.invoke()
    }, 1000)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "StartedScreen", fontSize = 22.sp)

//        Button(
//            modifier = Modifier
//                .width(200.dp)
//                .padding(bottom = 20.dp),
//            onClick = onStartClicked
//        ) { Text(text = "Get Started", fontSize = 22.sp) }
    }
}

@Preview(showBackground = true)
@Composable
fun StartedScreenPreview() {
    DataViewerTheme {
        StartedScreen(onStartClicked = {})
    }
}