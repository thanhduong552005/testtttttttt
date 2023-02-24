package com.example.khkt_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;

public class Suggestion_final extends AppCompatActivity {
    WebView webView;
    public String html;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_final);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            html = null;
        } else{
            html = extras.getString("HTML");
        }

        WebView webView = (WebView) findViewById(R.id.wv_final);
        String result = "{\"someKey\":\"someValue\"}";
        String encodedHtml = Base64.encodeToString(html.getBytes(), Base64.NO_PADDING);
        webView.loadData(encodedHtml, "text/html", "base64");
    }



}