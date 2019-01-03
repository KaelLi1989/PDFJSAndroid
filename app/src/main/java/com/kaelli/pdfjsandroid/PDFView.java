package com.kaelli.pdfjsandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

/**
 * Created by KaelLi on 2019/1/3.
 */
public class PDFView extends FrameLayout {
    private final static String PDFJS_PREFIX = "file:///android_asset/pdf_js/web/viewer.html?file=";
    private WebView webView;

    public PDFView(Context context) {
        super(context);
        init();
    }

    public PDFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PDFView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        webView = new WebView(getContext());
        addView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
    }

    public void loadLocalPDF(String path) {
        webView.loadUrl(PDFJS_PREFIX + "file://" + path);
    }

    public void loadOnlinePDF(String url) {
        webView.loadUrl(PDFJS_PREFIX + url);
    }
}
