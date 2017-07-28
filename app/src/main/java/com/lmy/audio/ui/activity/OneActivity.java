package com.lmy.audio.ui.activity;

import android.os.Bundle;

import android.widget.TextView;

import com.lmy.audio.R;




import butterknife.BindView;
import lmy.com.utilslib.base.ui.activity.BaseActivity;


/**
 * Created by lmy on 2017/7/5
 */

public class OneActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getContentView() {
        return R.layout.activity_one;
    }

    @Override
    protected String setTextTitle() {
        return "OneActivity";
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTopRightButton("右边", new OnClickListener() {
            @Override
            public void onClick() {
                startNextActivity(TwoActivity.class);

            }
        });
    }

    @Override
    protected void initData() {

    }
}
