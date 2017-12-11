package com.thirio.android.utils;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by abhinav on 7/12/17.
 */

public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }

    public void setId() {

    }
}