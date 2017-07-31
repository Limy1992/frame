package lmy.com.utilslib.web;

/**
 * 设置url
 * Created by lmy on 2017/7/31
 */

public class TenetWebView extends WebViewFile {
    @Override
    protected void loadWebViewUrl() {
        webView.loadUrl("http://www.html5teach.com/gongfang/iFully/input.do?ilifeName=");
    }
}
