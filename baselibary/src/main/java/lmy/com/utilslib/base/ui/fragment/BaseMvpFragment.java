package lmy.com.utilslib.base.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import lmy.com.utilslib.mvp.base.presenter.BasePresenter;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.Utils;

/**
 * Created by on 2017/12/9.
 *
 * @author lmy
 */

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>>
        extends BaseJumpFragment {
    protected T mPresent;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.e("onActivityCreated");
        mPresent = createPresent();
        mPresent.attachView((V) this);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract T createPresent();

    @Override
    public void onDestroy() {
        mPresent.detach();
        super.onDestroy();
    }
}
