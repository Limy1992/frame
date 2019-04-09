package com.lmy.model.web.js;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.lmy.model.web.web.X5WebView;

/**
 * js交互
 * CreateDate:2019/3/17
 * Author:lmy
 */
public class BridgeJsControl {

    private final X5WebView mWebView;
    private final Context mContext;

    public BridgeJsControl(X5WebView webView, Context context) {
        this.mWebView = webView;
        this.mContext = context;
        queryBigImage();
    }

    private void queryBigImage() {
        mWebView.registerHandler("queryBigImage", new BridgeHandler() {
            @Override
            public void handler(final String data, CallBackFunction function) {
                System.out.println("data="+data);
            }
        });
    }

    public boolean onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else{
            return true;
        }
        return false;
    }
}

