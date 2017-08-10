package com.lmy.audio.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lmy.audio.R;
import com.zhihu.matisse.Matisse;

import butterknife.BindView;

import lmy.com.utilslib.base.ui.activity.BaseActivity;
import lmy.com.utilslib.utils.BitmapUtils;
import lmy.com.utilslib.utils.CommonManger;
import lmy.com.utilslib.utils.LogUtils;


/**
 * Created by lmy on 2017/7/5
 */

public class OneActivity extends BaseActivity {

    @BindView(R.id.one_lv)
    ImageView oneLv;

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

    public void oneClick(View view) {
        startAlbum();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CommonManger.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            long startTime = System.currentTimeMillis();
            String filePath = contentFile(Matisse.obtainResult(data).get(0));
            oneLv.setImageBitmap(BitmapUtils.bitmapOptions(filePath, 800, 800));
            long endTime = System.currentTimeMillis() - startTime;
            LogUtils.e("endTime=" + endTime);
        }
    }
}
