package lmy.com.utilslib.base.ui.activity.more;

import android.os.Bundle;

import lmy.com.utilslib.mvp.base.presenter.BasePresenter;

/**
 * 1、加载更多和刷刷新， 带有title
 * Created by on 2018/8/8.
 *
 * @author lmy
 */
public abstract class BaseMoreMvpTitleActivity<V, T extends BasePresenter<V>> extends BaseMoreMvcTitleActivity {
    protected T mPresent;

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }

    @Override
    protected void initMvpPresenter() {
        mPresent = createPresent();
        mPresent.attachView((V) this);
    }

    protected abstract T createPresent();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void superRequestData() {
        mPresent.requestData();
    }
}
