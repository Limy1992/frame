package lmy.com.utilslib.base.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Unbinder;
import lmy.com.utilslib.R;

/**
 * Activity基类
 * Created by Matthew_Chen on 2017/4/14.
 *
 * @author lmy
 */

public class SuperTopBarBaseActivity extends RxAppCompatActivity {
    public Toolbar toolbar;
    public FrameLayout viewContent;
    public TextView tvTitle;
    public Unbinder bind;
    public Context mContext;
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setNotTitle();
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_base_top_bar);
        //loadViewStub
        initViewStub();
        initView();
        setContentViews(savedInstanceState);
        //其他配置
        setAdditionConfigure();
        //初始化titleBar
        initTitleBar();
    }

    protected void setNotTitle() {

    }

    protected void initViewStub() {
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewContent = (FrameLayout) findViewById(R.id.viewContent);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

    }

    protected void setAdditionConfigure() {
    }

    protected void setContentViews(Bundle savedInstanceState) {
    }

    private void initTitleBar() {
        ImmersionBar.setTitleBar(this, toolbar);
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarDarkFont(true, 0.2f)
                .init();
    }
    protected ViewStub getViewStub() {
        return (ViewStub) findViewById(R.id.tl_view_stub);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        if (immersionBar != null) {
            immersionBar.destroy();
            immersionBar = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**分享或者登陆回调*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}





