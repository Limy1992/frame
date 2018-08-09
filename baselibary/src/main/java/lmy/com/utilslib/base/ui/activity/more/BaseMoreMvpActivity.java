package lmy.com.utilslib.base.ui.activity.more;

import android.os.Bundle;

import lmy.com.utilslib.mvp.base.presenter.BasePresenter;

/**
 * 加载更多和刷，如果刷新的界面， 没有title
 * Created by on 2018/8/8.
 *
 * @author lmy
 */
public abstract class BaseMoreMvpActivity<V, T extends BasePresenter<V>> extends SuperMoreActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }

    protected T mPresent;

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
        if (mPresent != null) {
            mPresent.detach();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void superRequestData() {
        mPresent.requestData();
    }
}
