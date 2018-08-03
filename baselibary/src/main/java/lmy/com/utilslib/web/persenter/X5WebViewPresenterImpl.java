package lmy.com.utilslib.web.persenter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.view.View;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebViewClient;

import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.web.ui.X5WebView;
import lmy.com.utilslib.web.view.IX5WebView;

/**
 * webView
 * Created by on 2018/1/9.
 *
 * @author lmy
 */

public class X5WebViewPresenterImpl {
    protected X5WebView webView;
    protected IX5WebView mWebView;
    private OnWebUrlSubStringListener mOnWebUrlSubStringListener;

    public void loadShowWebView(final IX5WebView iWebView) {
        this.mWebView = iWebView;
        webView = iWebView.onWebView();
        String url = iWebView.onWebUrl();

        String imUrl = "url is null";
        if (url != null) {
            if (url.contains("?")) {
                imUrl = url + "&isOut=0";
            } else {
                imUrl = url + "?isOut=0";
            }
        }

        webView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {

            @Override
            public boolean onJsConfirm(com.tencent.smtt.sdk.WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            @Override
            public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                if (mWebView.onProgress() != null) {
                    mWebView.onProgress().setProgress(newProgress);
                    if (100 > newProgress) {
                        mWebView.onProgress().setVisibility(View.VISIBLE);
                    } else {
                        mWebView.onProgress().setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public boolean onJsAlert(com.tencent.smtt.sdk.WebView arg0, String arg1, String arg2,
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
                                             com.tencent.smtt.sdk.WebChromeClient.FileChooserParams fileChooserParams) {
//                filePathCallBacks(filePathCallback);
                return true;
            }

            @Override
            public void onReceivedTitle(com.tencent.smtt.sdk.WebView webView, String title) {
                mWebView.setTitle(title);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String url) {
                super.onPageFinished(webView, url);
                if (mOnWebUrlSubStringListener != null) {
                    mOnWebUrlSubStringListener.onLoadOver(url);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.webViewLoadOver();
                    }
                }, 1500);
            }

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                LogUtils.d("shouldOverrideUrlLoading");
                return false;
            }

            @Override
            public void onPageStarted(com.tencent.smtt.sdk.WebView webView, String url, Bitmap bitmap) {
                super.onPageStarted(webView, url, bitmap);
                if (mOnWebUrlSubStringListener != null) {
                    mOnWebUrlSubStringListener.onLoadStart(url);
                }
            }
        });


        webView.loadUrl(imUrl);
    }

    public void onDestroy() {
        try {
            if (webView != null) {
                webView.getSettings().setBuiltInZoomControls(true);
                webView.setVisibility(View.GONE);
                webView.removeAllViews();
                webView.destroy();
                webView = null;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * webView加载监听
     */
    public void setOnWebUrlSubStringListener(OnWebUrlSubStringListener onWebUrlSubStringListener) {
        this.mOnWebUrlSubStringListener = onWebUrlSubStringListener;
    }

    public interface OnWebUrlSubStringListener {
        /**
         * 加载完毕
         */
        void onLoadOver(String url);

        /**
         * 开始加载
         */
        void onLoadStart(String url);
    }
}
