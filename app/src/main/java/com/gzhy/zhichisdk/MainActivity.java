package com.gzhy.zhichisdk;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    String url = "http://www.sobot.com/chat/h5/index.html?sysNum=5244e97722494fa3bb0f45c4e3e3a4c4&source=2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Information info = new Information();
                info.setAppkey("c6b29ad917ac4e8a9aac09a928bd55b4");
                SobotApi.startSobotChat(MainActivity.this, info);
            }
        });
//        webView = (WebView) findViewById(R.id.web_chat);
//        initWebView();
//        webView.loadUrl(url);
    }
    private void initWebView() {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
            } catch (Exception e) {
                //ignor
            }
        }
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.getSettings().setDefaultFontSize(16);
        webView.getSettings().setTextZoom(100);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 设置可以使用localStorage
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString() + " sobot");

        // 应用可以有数据库
        webView.getSettings().setDatabaseEnabled(true);

        // 应用可以有缓存
        webView.getSettings().setAppCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //注释的地方是打开其它应用，比如qq
                /*if (url.startsWith("http") || url.startsWith("https")) {
                    return false;
                } else {
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);
                    return true;
                }*/
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

            }

//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                sobot_webview_goback.setEnabled(mWebView.canGoBack());
//                sobot_webview_forward.setEnabled(mWebView.canGoForward());
//                if (!mUrl.replace("http://", "").replace("https://", "").equals(view.getTitle())) {
//                    setTitle(view.getTitle());
//                }
//            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                LogUtils.i("网页--title---：" + title);
                if (!url.replace("http://", "").replace("https://", "").equals(title)) {
                    setTitle(title);
                }
            }

//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress > 0 && newProgress < 100) {
//                    mProgressBar.setVisibility(View.VISIBLE);
//                    mProgressBar.setProgress(newProgress);
//                } else if (newProgress == 100) {
//                    mProgressBar.setVisibility(View.GONE);
//                }
//            }
        });
    }
}
