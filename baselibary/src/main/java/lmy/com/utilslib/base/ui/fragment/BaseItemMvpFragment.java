package lmy.com.utilslib.base.ui.fragment;

import lmy.com.utilslib.mvp.base.presenter.BasePresenter;
import lmy.com.utilslib.utils.LogUtils;

/**
 * 多个相同的fragment
 * Created by on 2018/5/9.
 *
 * @author lmy
 */
public abstract class BaseItemMvpFragment<V, T extends BasePresenter<V>>
        extends BaseItemFragment {

    protected T mPresent;

    @Override
    protected void onFragmentFirstVisible() {
        LogUtils.d("onFragmentFirstVisible");
        mPresent = createPresent();
        mPresent.attachView((V) this);
        initView();
        initData();
    }

    protected abstract T createPresent();

    protected abstract void initView();

    protected abstract void initData();
}
