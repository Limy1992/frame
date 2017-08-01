package lmy.com.utilslib.web;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.utils.TbsLog;

import butterknife.BindView;
import lmy.com.utilslib.R;
import lmy.com.utilslib.R2;
import lmy.com.utilslib.base.ui.activity.BaseActivity;

/**
 * 视频播放和初始化配置
 * Created by lmy on 2017/7/31
 */

public class WebViewVideo extends BaseActivity {
    @BindView(R2.id.web_fl)
    ViewGroup web_fl;
    protected X5WebView webView;

    @Override
    protected int getContentView() {
        initWindow();
        return R.layout.web_view_activity;
    }

    @Override
    protected String setTextTitle() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        webView = new X5WebView(this, null);
        //初始化WebViewClient
        initWebViewClient();
        web_fl.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            View myVideoView;
            View myNormalView;
            IX5WebChromeClient.CustomViewCallback callback;

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view,
                                         IX5WebChromeClient.CustomViewCallback customViewCallback) {
                FrameLayout normalView = (FrameLayout) findViewById(R.id.forum_context2);
                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
                viewGroup.removeView(normalView);
                viewGroup.addView(view);
                myVideoView = view;
                myNormalView = normalView;
                callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(myNormalView);
                }
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                     JsResult arg3) {
                //这里写入你自定义的window alert
                return super.onJsAlert(null, arg1, arg2, arg3);
            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {
                filePathCallBacks(filePathCallback);
                return true;
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                setTitleText(s);
            }

        });
    }

    @Override
    protected void initData() {
        //网络请求 提供之类取实现
        netResult();
        WebSettings webSetting = webView.getSettings();
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
        long time = System.currentTimeMillis();
        //js交互
        interactiveJS();
        //加载url地址
        loadWebViewUrl();
        TbsLog.d("time-cost", "cost time: " + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    private void initWindow() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
            getWindow()
                    .setFlags(
                            android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                            android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webView != null)
            webView.destroy();
        super.onDestroy();

    }

    protected void netResult() {}
    protected void interactiveJS() {}
    protected void loadWebViewUrl() {}
    protected void initWebViewClient() {}
    protected void filePathCallBacks(ValueCallback<Uri[]> filePathCallback) {}

}
