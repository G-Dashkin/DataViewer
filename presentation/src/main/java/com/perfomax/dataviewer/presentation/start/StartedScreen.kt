package com.perfomax.dataviewer.presentation.start

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.width100
import com.perfomax.ui.R

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
        Image(
            modifier = Modifier.width(width100)
                               .height(width100),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo)
        )
        Text(text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge)

    }
}

@Preview(showBackground = true)
@Composable
fun StartedScreenPreview() {
    DataViewerTheme {
        StartedScreen(onStartClicked = {})
    }
}