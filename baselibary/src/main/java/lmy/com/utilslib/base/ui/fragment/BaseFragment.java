package lmy.com.utilslib.base.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import lmy.com.utilslib.utils.Utils;

/**
 * Fragment基类
 * Created by lmy on 2017/7/6
 */

public abstract class BaseFragment extends BaseHomeFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    public void startNextActivity(Class activity) {
        Intent intent = new Intent(Utils.getContext(), activity);
        startActivity(intent);
    }

    public void startNextActivity(Bundle bundle, Class activity) {
        Intent intent = new Intent(Utils.getContext(), activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
