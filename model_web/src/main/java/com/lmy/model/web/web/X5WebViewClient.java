package com.lmy.model.web.web;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.Message;
import com.lmy.model.web.js.BridgeUtil;
import com.lmy.model.web.ui.ProjectWebActivity;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * x5监听设置
 * CreateDate:2019/4/9
 * Author:lmy
 */
public class X5WebViewClient {

    private final X5WebViewModel x5WebViewModel;

    public X5WebViewClient(Context context){
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        x5WebViewModel = ProjectWebActivity.obtainViewModel(fragmentActivity);
        x5WebViewModel.getX5WebViewMutableLiveData()
                .observe(fragmentActivity, new Observer<X5WebView>() {
                    @Override
                    public void onChanged(@Nullable X5WebView x5WebView) {
                        if (x5WebView != null) {
                            initWebClient(x5WebView);
                        }
                    }
                });
    }

    private void initWebClient(final X5WebView x5WebView) {
        x5WebView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {

            @Override
            public boolean onJsConfirm(com.tencent.smtt.sdk.WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            @Override
            public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
            }

            @Override
            public void onReceivedTitle(com.tencent.smtt.sdk.WebView webView, String title) {
                x5WebViewModel.setWebViewTitle(title);
            }
        });

        x5WebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView web, String url) {
                super.onPageFinished(web, url);
                BridgeUtil.webViewLoadLocalJs(web, BridgeWebView.toLoadJs);
                if (x5WebView.getStartupMessage() != null) {
                    for (Message m : x5WebView.getStartupMessage()) {
                        x5WebView.dispatchMessage(m);
                    }
                    x5WebView.setStartupMessage(null);
                }
                onCustomPageFinishd(web,url);

//                mWebView.webViewLoadOver();
            }

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView web, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                try {
                    url = URLDecoder.decode(url, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
                    x5WebView.handlerReturnData(url);
                    return true;
                } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
                    x5WebView.flushMessageQueue();
                    return true;
                } else {
                    return onCustomShouldOverrideUrlLoading(url) || super.shouldOverrideUrlLoading(web, url);
                }
            }

            @Override
            public void onPageStarted(com.tencent.smtt.sdk.WebView webView, String url, Bitmap bitmap) {
                super.onPageStarted(webView, url, bitmap);
            }
        });
    }

    public boolean onCustomShouldOverrideUrlLoading(String url) {
        return false;
    }


    protected void onCustomPageFinishd(com.tencent.smtt.sdk.WebView webView , String url){

    }
}
