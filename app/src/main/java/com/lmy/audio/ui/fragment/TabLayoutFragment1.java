package com.lmy.audio.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mzule.activityrouter.router.Routers;
import com.lmy.audio.R;
import com.lmy.audio.ui.activity.TwoActivity;

import butterknife.BindView;
import lmy.com.utilslib.base.ui.fragment.BaseFragment;
import lmy.com.utilslib.utils.Utils;

/**
 * Created by lmy on 2017/7/12
 */

public class TabLayoutFragment1 extends BaseFragment {
    @BindView(R.id.tab_layout_tv)
    TextView tabLayoutTv;

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_tab_layout3;
    }

    @Override
    protected void initView() {
        Log.e("tag", "tabLayout1");
        tabLayoutTv.setText("tabLayout1");


        tabLayoutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //组件间通信
                Routers.open(Utils.getContext(), "audio://shop?name=name&age=10");
            }
        });

    }

    @Override
    protected void initData() {

    }
}
