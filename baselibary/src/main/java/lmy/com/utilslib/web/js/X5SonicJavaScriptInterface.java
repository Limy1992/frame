package lmy.com.utilslib.web.js;

import android.webkit.JavascriptInterface;

import lmy.com.utilslib.net.rx.RxBus;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.web.ui.X5WebView;


/**
 * 交互JS
 * Created by on 2018/1/8.
 *
 * @author lmy
 */

public class X5SonicJavaScriptInterface {
    private final X5WebView mWebView;

    public X5SonicJavaScriptInterface(X5WebView webView) {
        this.mWebView = webView;
    }

    @JavascriptInterface
    public void playbackRateNum(int playbackRate){

        RxBus.getInstanceBus().post((float)playbackRate);

        LogUtils.e("playbackRate="+playbackRate);
    }

}
