package com.eotw95.wantnote.screen

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebWant(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            // Permission access mixed contents HTTP and HTTPS
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            // not load cache
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            loadUrl(url)
        }
    }
    )
}