package com.lmy.audio.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lmy.audio.R;
import com.lmy.audio.ui.activity.OneActivity;

import java.util.List;

import butterknife.BindView;

import io.reactivex.Observable;
import lmy.com.utilslib.base.ui.fragment.BaseFragment;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.bean.DataBean;
import lmy.com.utilslib.net.ActivityLifeCycleEvent;
import lmy.com.utilslib.net.Api;
import lmy.com.utilslib.net.HttpUtil;

import lmy.com.utilslib.net.ProgressSubscriber;
import lmy.com.utilslib.utils.LogUtils;


public class TabFragment1 extends BaseFragment {
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.bu)
    Button bu;

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initView() {
        LogUtils.e("TabFragment1");



        Observable<BaseHttpResult<List<DataBean.ListBean>>> serviceData = Api.getDefault().getServiceData("f348719c-80da-45d0-a7c6-fbd9e8dc28c0");
        HttpUtil.getInstance()
                .toSubscribe(serviceData,  new ProgressSubscriber<List<DataBean.ListBean>>(getActivity()) {
                    @Override
                    protected void _onNext(List<DataBean.ListBean> o) {
                        txt.setText(o.toString());
                    }

                    @Override
                    protected void _onError(String message) {
                        txt.setText(message);
                    }
                },"cacheKey", ActivityLifeCycleEvent.DESTROY, lifecycleSubject, true, false);


        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(OneActivity.class);
            }
        });

    }

    @Override
    protected void initData() {

    }
}
