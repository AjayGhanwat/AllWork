package com.bridgelabz.googlenews

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URL

public class FullNews : AppCompatActivity() {
    public var url:String ?= null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_news)

        var bundle:Bundle = intent.extras

        url= bundle.getString("url")

        var webView:WebView = findViewById(R.id.webNewsView)

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = MyWebViewClient()

        webView.loadUrl(url)

        webView.isHorizontalScrollBarEnabled = false
    }

    class MyWebViewClient : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.url.toString())
            }
            return true
        }
    }
}
