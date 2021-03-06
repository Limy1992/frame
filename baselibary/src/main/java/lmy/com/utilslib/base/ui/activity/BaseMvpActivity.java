package lmy.com.utilslib.base.ui.activity;

import android.os.Bundle;

import lmy.com.utilslib.mvp.base.presenter.BasePresenter;

/**
 * 使用MVP activity继承此类
 * Created by on 2017/12/9.
 *
 * @author lmy
 */

public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends SuperInitActivity {
    protected T mPresent;

    @Override
    protected void initMvpPresenter() {
        mPresent = createPresent();
        mPresent.attachView((V) this);
    }

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
