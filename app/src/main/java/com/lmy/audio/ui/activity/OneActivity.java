package com.lmy.audio.ui.activity;

import android.content.Intent;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.lmy.audio.R;
import java.util.List;

import butterknife.BindView;

import lmy.com.utilslib.base.ui.activity.BaseActivity;

import lmy.com.utilslib.utils.LogUtils;

import lmy.com.utilslib.utils.Utils;
import lmy.com.utilslib.zhihu.Matisse;

import static lmy.com.utilslib.utils.CommonManger.REQUEST_CODE_CHOOSE;


/**
 * Created by lmy on 2017/7/5
 */

public class OneActivity extends BaseActivity {

    @BindView(R.id.one_lv)
    ImageView oneLv;
    @BindView(R.id.tv)
    TextView tv;

    public static final String URL = "http://t.i-fully.cn/ilife/personal/uploadfrontimg";
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

        if (Utils.getNavBarHeight() <= 0) {
            tv.setText("没有虚拟键");
        }else {
            tv.setText(String.valueOf(Utils.getNavBarHeight()));
        }

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
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uris = Matisse.obtainResult(data);
            List<String> list = Matisse.obtainPathResult(data);

            for (Uri uri : uris) {
                LogUtils.e("uris="+uri.toString());
            }

            for (String s : list) {
                LogUtils.e("s="+s);
            }

        }
    }
}
