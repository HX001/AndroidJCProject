package com.practice.androidprojectjcbased.SourceCodeWebView

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.practice.androidprojectjcbased.SourceCodeWebView.Retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WebViewScreen(
     url: String
) {
    var pageSource by remember { mutableStateOf("Loading...") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(url) {
        scope.launch(Dispatchers.IO) {
            try {
                pageSource = RetrofitInstance.api.getPageSource(url).body()!!
            } catch (e: Exception) {
                pageSource = "Failed to load page source."
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())
        ) {
            Text(
                text = pageSource,
                color = Color.Blue
            )
        }
        WebViewComposable(url = url, modifier = Modifier.weight(1f))
    }

}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComposable(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        modifier = modifier
    )
}