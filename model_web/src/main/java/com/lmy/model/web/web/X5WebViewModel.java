package com.lmy.model.web.web;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


/**
 * CreateDate:2019/4/9
 * Author:lmy
 */
public class X5WebViewModel extends ViewModel {
    private final MutableLiveData<X5WebView> x5WebViewMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> mWebViewTitle = new MutableLiveData<>();

    public MutableLiveData<X5WebView> getX5WebViewMutableLiveData() {
        return x5WebViewMutableLiveData;
    }

    public void setX5WebViewMutableLiveData(X5WebView x5WebView){
        x5WebViewMutableLiveData.setValue(x5WebView);
    }

    public MutableLiveData<String> getWebViewTitle() {
        return mWebViewTitle;
    }

     void setWebViewTitle(String webViewTitle){
        mWebViewTitle.setValue(webViewTitle);
    }

    public void onDestroy()  {
        try {
            X5WebView webView = x5WebViewMutableLiveData.getValue();
            if (webView != null) {
                webView.stopLoading();
                webView.removeAllViewsInLayout();
                webView.removeAllViews();
                webView.setWebViewClient(null);
                webView.destroy();
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

    }
}
