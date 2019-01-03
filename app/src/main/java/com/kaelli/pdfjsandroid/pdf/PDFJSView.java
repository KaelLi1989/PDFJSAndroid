package com.kaelli.pdfjsandroid.pdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

/**
 * Created by PDT on 02.06.2017.
 */
public class PDFJSView extends FrameLayout {
    public static final String PDFJS_ASSETS_PATH = "file:///android_asset/pdf_js/web/viewer.html";
    private WebView webView;
    private APDFJSInterface apdfjsInterface = new APDFJSInterface();

    public PDFJSView(Context context) {
        super(context);
        init();
    }

    public PDFJSView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PDFJSView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void init() {
        webView = new WebView(getContext());
        addView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.addJavascriptInterface(apdfjsInterface, "APDFJS");
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
    }

    public void loadFromAssets(String pdfAssetsPath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.loadUrl("file:///android_asset/pdf_js/web/viewer.html?file=file:///android_asset/" +
                    Uri.encode(pdfAssetsPath, "UTF-8"));
        } else {
            apdfjsInterface.fileName = "file:///android_asset/" + pdfAssetsPath;
            webView.loadUrl(PDFJS_ASSETS_PATH);
        }
    }

    public void loadFromFile(String pdfFilePath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.loadUrl("file:///android_asset/pdf_js/web/viewer.html?file=file://" +
                    Uri.encode(pdfFilePath, "UTF-8"));
        } else {
            apdfjsInterface.fileName = "file://" + pdfFilePath;
            webView.loadUrl(PDFJS_ASSETS_PATH);
        }
    }

    public void loadFromUrl(String url) {
        String res = Uri.encode(url, "UTF-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.loadUrl("file:///android_asset/pdf_js/web/viewer.html?file=" + url);
        }
//        else {
//            apdfjsInterface.fileName = "file://" + pdfFilePath;
//            webView.loadUrl(PDFJS_ASSETS_PATH);
//        }
    }

    public class APDFJSInterface {
        String fileName = "";
        public String getFileName() {
            return fileName;
        }
    }
}
