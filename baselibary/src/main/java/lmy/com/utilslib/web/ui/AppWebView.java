package lmy.com.utilslib.web.ui;

import android.os.Bundle;
import android.widget.ProgressBar;

import butterknife.BindView;
import lmy.com.utilslib.R;
import lmy.com.utilslib.R2;
import lmy.com.utilslib.base.ui.activity.BaseTitleActivity;
import lmy.com.utilslib.web.persenter.X5WebViewPresenterImpl;
import lmy.com.utilslib.web.view.IX5WebView;

/**
 * webView
 * Created by on 2018/5/12.
 *
 * @author lmy
 */
public class AppWebView extends BaseTitleActivity {
    @BindView(R2.id.web_progress)
    ProgressBar webProgress;
    private X5WebView webView;
    private X5WebViewPresenterImpl webViewVideo;
    @Override
    protected int getContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        webView = (X5WebView) findViewById(R.id.webview);
    }

    @Override
    protected void initData() {
        webViewVideo = new X5WebViewPresenterImpl();
        webViewVideo.loadShowWebView(new IX5WebView() {
            @Override
            public X5WebView onWebView() {
                return webView;
            }

            @Override
            public String onWebUrl() {
                return getIntents("pagerUrl");
            }

            @Override
            public void webViewLoadOver() {
            }

            @Override
            public void setTitle(String title) {
                setTitleText(title);
            }

            @Override
            public ProgressBar onProgress() {
                return webProgress;
            }

        });
    }
}
