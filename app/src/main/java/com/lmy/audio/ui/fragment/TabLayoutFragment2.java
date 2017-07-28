package com.lmy.audio.ui.fragment;

import android.util.Log;
import android.widget.TextView;

import com.lmy.audio.R;

import butterknife.BindView;
import lmy.com.utilslib.base.ui.fragment.BaseFragment;

/**
 * Created by lmy on 2017/7/12
 */

public class TabLayoutFragment2 extends BaseFragment {
    @BindView(R.id.tab_layout_tv)
    TextView tabLayoutTv;

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_tab_layout3;
    }

    @Override
    protected void initView() {
        Log.e("tag", "tabLayout2");
        tabLayoutTv.setText("tabLayout2");
    }

    @Override
    protected void initData() {

    }

}
