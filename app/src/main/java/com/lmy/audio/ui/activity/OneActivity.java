package com.lmy.audio.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.lmy.audio.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;


import java.util.List;

import butterknife.BindView;
import lmy.com.utilslib.base.ui.activity.BaseActivity;


/**
 * Created by lmy on 2017/7/5
 */

public class OneActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE = 23;

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

    public void oneClick(View view){
        Matisse.from(OneActivity.this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
        }
    }
}
