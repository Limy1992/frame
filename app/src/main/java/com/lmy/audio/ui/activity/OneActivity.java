package com.lmy.audio.ui.activity;

import android.content.Intent;


import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.lmy.audio.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;

import butterknife.BindView;

import lmy.com.utilslib.base.ui.activity.BaseActivity;

import lmy.com.utilslib.utils.BitmapUtils;
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
        } else {
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
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE:
                    List<Uri> uris = Matisse.obtainResult(data);        //uri
                    List<String> list = Matisse.obtainPathResult(data); //文件绝对路径
                    UCrop.of(Uri.fromFile(new File(list.get(0))), Uri.fromFile(new File(list.get(0))))
                            .withAspectRatio(16, 16)
                            .withMaxResultSize(1000, 1000)
                            .start(this);
                    break;
                case UCrop.REQUEST_CROP:
                    Uri resultUri = UCrop.getOutput(data);
                    LogUtils.e("resultUri=" + resultUri);
                    Glide.with(this).load(resultUri).into(oneLv);
                    break;
            }
        }

        //获取文件失败
        if (resultCode == UCrop.RESULT_ERROR && requestCode == UCrop.REQUEST_CROP) {
            LogUtils.e("resultUri=" + UCrop.getError(data));
        }
    }
}
