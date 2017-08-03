package com.lmy.audio.ui.fragment;

import android.graphics.Color;
import android.widget.TextView;

import com.lmy.audio.R;

import butterknife.BindView;
import lmy.com.utilslib.base.ui.fragment.BaseFragment;


public class TabFragment3 extends BaseFragment {

    @BindView(R.id.txt)
    TextView txt;
    @Override
    protected int getFragmentView() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initView() {
        txt.setBackgroundColor(Color.YELLOW);
    }

    @Override
    protected void initData() {

    }
}
