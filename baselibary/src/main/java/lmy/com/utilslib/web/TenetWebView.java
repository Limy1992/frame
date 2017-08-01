package lmy.com.utilslib.web;

import android.webkit.JavascriptInterface;

import com.github.mzule.activityrouter.router.Routers;

import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.Utils;

/**
 * 设置url和js交互
 * Created by lmy on 2017/7/31
 */

public class TenetWebView extends WebNetResult {

    @Override
    protected void interactiveJS() {
        //hello为交互的标识
        webView.addJavascriptInterface(new JSInteractive(webView), "hello");
    }

    @Override
    protected void loadWebViewUrl() {
//        webView.loadUrl("https://mp.weixin.qq.com/s/iFX9gOJm-gYnuP9PpR3n4Q");
        webView.loadUrl("file:///android_asset/title.html");
//        webView.loadUrl("https://www.baidu.com");
    }

    //与js交互
    private static class JSInteractive {
        private final X5WebView mWebView;

        JSInteractive(X5WebView webView) {
            this.mWebView = webView;
        }

        @JavascriptInterface
        public void showAndroid() {
            String info = "来自手机内的内容！！！";
            mWebView.loadUrl("javascript:show('" + info + "')");
        }

        @JavascriptInterface
        public void javaMethod(String string) {
            LogUtils.e(string);
            //通过Routers实行组件跳转,或者隐士启动
            Routers.open(Utils.getContext(), "audio://net?str=" + string);
        }
    }
}
