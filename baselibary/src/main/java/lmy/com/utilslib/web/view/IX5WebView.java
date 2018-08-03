package lmy.com.utilslib.web.view;

import android.widget.ProgressBar;

import lmy.com.utilslib.web.ui.X5WebView;

/**
 * 提供和回调
 * Created by on 2018/1/9.
 *
 * @author lmy
 */

public interface IX5WebView {
    X5WebView onWebView();
    String onWebUrl();
    void webViewLoadOver();
    void setTitle(String title);

    ProgressBar onProgress();

}
