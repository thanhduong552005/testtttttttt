package com.example.khkt_2023;
//android:theme="@style/Theme.KHKT_2023" cua androimainfest
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class StoryDetail extends AppCompatActivity {
    WebView webView;
    public String image_path;
    public String html;
    private String loadUrl = "https://8ea8-27-78-200-299.ap.ngrok.io/v1/getbaihoc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            image_path = null;
            html = null;
        } else {
            image_path = extras.getString("IMAGE_PATH");
            html = extras.getString("HTML");
        }

        WebView webView = (WebView) findViewById(R.id.wv_detail);
        //String unencodedHtml = "<html><body>'%23' is the percent code for ‘#‘ </body></html>";
        String result = "{\"someKey\":\"someValue\"}";
        String encodedHtml = Base64.encodeToString(html.getBytes(), Base64.NO_PADDING);
        webView.loadData(encodedHtml, "text/html", "base64");
        webView.loadUrl(loadUrl);

    }

}


//            webView = findViewById(R.id.ww_detail);
//            WebSettings webSettings = webView.getSettings();
//            webSettings.setBuiltInZoomControls(true);
//            webSettings.setSupportZoom(true);
//            webSettings.setJavaScriptEnabled(true);
//            webView.setWebViewClient(new Cacllback());
//            webView.loadUrl("file:///android_asset/index.html");
//        }
//        private class Cacllback extends WebViewClient {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        }
//        // quay trowr laij trang truoc
//        @Override
//        public void onBackPressed() {
//            if (webView != null && webView.canGoBack()) {
//                webView.goBack();
//            } else {
//                super.onBackPressed();
//            }