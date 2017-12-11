package com.thirio.android.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thirio.android.R;
import com.thirio.android.utils.GifWebView;

import java.io.IOException;
import java.io.InputStream;

public class Thirio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_thirio);
        InputStream stream = null;
        try {
            stream = getAssets().open("machine.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebView webView=(WebView)findViewById(R.id.machine);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        GifWebView view =  new GifWebView(this, "file:///android_asset/machine.gif");
        view.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        webView.addView(view);
//        View machine=findViewById(R.id.machine);
//        machine.inflate(this,1,null);

    }
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
