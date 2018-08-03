package lmy.com.utilslib.web.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;

import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.web.js.X5SonicJavaScriptInterface;

/**
 * 腾讯X5 WebView
 * Created by lmy on 2017/7/31
 */

public class X5WebView extends WebView {

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        initWebViewSettings();
        LogUtils.d("X5WebView");
        this.getView().setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        addJavascriptInterface(new X5SonicJavaScriptInterface(this), "JSInterface");

        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        //设置视频播放
        Bundle data = new Bundle();
        data.putBoolean("standardFullScreen", false);
        //true表示标准全屏，false表示X5全屏；不设置默认false，
        data.putBoolean("supportLiteWnd", false);
        //false：关闭小窗；true：开启小窗；不设置默认true，
        data.putInt("DefaultVideoScreen", 2);
        //1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
        if (getX5WebViewExtension() != null) {
            getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }

        //下面方法去掉右边滑动按钮
        IX5WebViewExtension ix5 = getX5WebViewExtension();
        if (ix5 != null) {
            ix5.setScrollBarFadingEnabled(false);
        }
    }

    public X5WebView(Context context) {
        super(context);
        setBackgroundColor(85621);
        LogUtils.d("X5WebView(content)");
    }
}
