package lmy.com.utilslib.web;

import android.graphics.Bitmap;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import lmy.com.utilslib.utils.LogUtils;

/**
 * 后台交互网络,比如webView需要显示评论等
 * Created by lmy on 2017/8/1
 */

public class WebNetResult extends WebViewFile {

    @Override
    protected void initWebViewClient() {
        webView.setWebViewClient(client);
    }

    @Override
    protected void netResult() {
        //网络操作
    }

    WebViewClient client = new WebViewClient() {
        //防止加载网页时调起系统浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //开始加载
        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            LogUtils.e("onPageStarted");
        }

        //加载成功
        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            LogUtils.e("onPageFinished");
        }

        //加载失败
        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
            LogUtils.e("onReceivedError");
        }
    };
}
