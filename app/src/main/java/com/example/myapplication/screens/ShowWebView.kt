package com.example.myapplication.screens

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ShowWebView(url: String) {
    val webState = rememberWebViewState(url = url)

    WebView(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp),
        state = webState,
        onCreated = {
            it.settings.run {
                javaScriptEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        },
        captureBackPresses = false,
        client = AccompanistWebViewClient(),
    )
}

