package com.lmy.audio.ui.fragment;

import android.util.Log;

import com.lmy.audio.R;

import lmy.com.utilslib.base.ui.fragment.BaseFragment;

/**
 * Created by lmy on 2017/7/12
 */

public class TabLayoutFragment3 extends BaseFragment {
    @Override
    protected int getFragmentView() {
        return R.layout.fragment_tab_layout3;
    }

    @Override
    protected void initView() {
        Log.e("tag", "tabLayout3");
    }

    @Override
    protected void initData() {

    }
}
