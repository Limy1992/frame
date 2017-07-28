package com.lmy.audio.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mzule.activityrouter.router.Routers;
import com.lmy.audio.R;

import butterknife.BindView;
import lmy.com.utilslib.base.ui.activity.BaseActivity;

/**
 * Created by lmy on 2017/7/5
 */

public class TwoActivity extends BaseActivity {
    @BindView(R.id.music_recycle)
    RecyclerView musicRecycle;

    @Override
    protected int getContentView() {
        return R.layout.activity_two;
    }

    @Override
    protected String setTextTitle() {
        return "TwoActivity";
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        musicRecycle.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {

    }

    public void click(View view) {
        startNextActivity(ThreeActivity.class);

    }

}
