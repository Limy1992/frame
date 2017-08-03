package com.lmy.audio.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lmy.audio.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lmy.com.utilslib.base.ui.fragment.BaseFragment;


public class TabFragment2 extends BaseFragment {
    @BindView(R.id.txt)
    TextView txt;
    @Override
    protected int getFragmentView() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initView() {
        txt.setBackgroundColor(Color.RED);
    }

    @Override
    protected void initData() {

    }


}
