package com.example.webview;


import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the WebView by its ID
        WebView myWebView = findViewById(R.id.webView);

        // Enable JavaScript if needed
        myWebView.getSettings().setJavaScriptEnabled(true);

        // Load the local HTML file from the assets folder
        myWebView.loadUrl("file:///android_asset/sample.html");
    }
}
