package lmy.com.utilslib.base.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import butterknife.ButterKnife;
import lmy.com.utilslib.R;

/**
 * 其他子类activity继承BaseActivity
 * Created by lmy on 2017/7/25
 */

public abstract class BaseActivity extends BasePagerJumpActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(getContentView(), viewContent);
        bind = ButterKnife.bind(this);
        //初始化设置 Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        //设置title
        setTitleText(setTextTitle());
        //默认每个页面都显示左上角关闭页面图片
        setTopLeftButton(R.drawable.ic_return_white_24dp, null);
        //初始化操作
        init(savedInstanceState);
        //网络加载
        initData();

    }

    protected abstract int getContentView();

    protected abstract String setTextTitle();

    protected abstract void init(Bundle savedInstanceState);


}
