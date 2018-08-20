package lmy.com.utilslib.base.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Fragment基类
 * Created by lmy on 2017/7/6
 */

public abstract class BaseFragment extends BaseCommonFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

}
