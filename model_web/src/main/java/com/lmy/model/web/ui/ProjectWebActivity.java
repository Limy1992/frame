package com.lmy.model.web.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lmy.model.web.R;
import com.lmy.model.web.R2;
import com.lmy.model.web.js.BridgeJsControl;
import com.lmy.model.web.web.X5WebView;
import com.lmy.model.web.web.X5WebViewClient;
import com.lmy.model.web.web.X5WebViewModel;

import butterknife.BindView;
import lmy.com.utilslib.base.ui.activity.BaseTitleActivity;
import lmy.com.utilslib.utils.ModelJumpCommon;

/**
 * webView
 * CreateDate:2019/4/9
 * Author:lmy
 */
@Route(path = ModelJumpCommon.WEB_VIEW_PROJECT)
public class ProjectWebActivity extends BaseTitleActivity {
    @BindView(R2.id.web_view_ff)
    FrameLayout webViewFf;
    @Autowired(name = ModelJumpCommon.KEY_WEB)
    String webViewUrl;
    private X5WebView webView;
    private BridgeJsControl mBridgeJsControl;
    private X5WebViewModel x5WebViewModel;

    @Override
    protected int getContentView() {
        return R.layout.web_activity_project;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initX5WebView();
        initButton();
        initWebLoad();
        mBridgeJsControl = new BridgeJsControl(webView, mContext);
    }

    private void initWebLoad() {
        webViewUrl = "http://m.lanwuzhe.com/app/competitionList/index.html";
        if (!TextUtils.isEmpty(webViewUrl)) {
            webView.loadUrl(webViewUrl);
        }
        new X5WebViewClient(mContext);
        FragmentActivity fragmentActivity = (FragmentActivity) this.mContext;
        x5WebViewModel = obtainViewModel(fragmentActivity);
        x5WebViewModel.setX5WebViewMutableLiveData(webView);
        x5WebViewModel.getWebViewTitle()
                .observe(fragmentActivity, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String title) {
                        setTitleText(title);
                    }
                });
    }

    private void initX5WebView() {
        webView = new X5WebView(this, null);
        webViewFf.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }

    private void initButton() {
        setTopLeftButton(new OnClickListener() {
            @Override
            public void onClick() {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initData() {
    }

    public static X5WebViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        return ViewModelProviders.of(fragmentActivity).get(X5WebViewModel.class);
    }

    @Override
    protected void onDestroy() {
        x5WebViewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mBridgeJsControl.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
