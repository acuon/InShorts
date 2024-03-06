package com.acuon.inshorts

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        //get data from intent
        val i = intent
        val newslink = i.getStringExtra("url")

        val view = findViewById<View>(R.id.webview) as WebView
        view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }
        view.settings.javaScriptEnabled = true
        newslink?.let { view.loadUrl(it) }
    }
}