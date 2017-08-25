package com.lmy.audio.ui.activity;

import android.content.Intent;


import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.lmy.audio.R;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
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
                    uarOptions(list);
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

    private void uarOptions(List<String> list) {
        UCrop.of(Uri.fromFile(new File(list.get(0))), Uri.fromFile(new File(list.get(0))))
                .withAspectRatio(16, 16)
                .withMaxResultSize(1000, 1000)
                .withOptions(options())
                .start(this);
    }

    private UCrop.Options options() {
        UCrop.Options options = new UCrop.Options();
        //设置titleBar颜色
        options.setToolbarColor(getResources().getColor(R.color.colorAccent));
        //设置状态栏颜色
        options.setStatusBarColor(getResources().getColor(R.color.colorAccent));
//        //设置裁剪图片可操作的手势
//        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
//        //是否隐藏底部容器，默认显示
//        options.setHideBottomControls(true);
//        //是否能调整裁剪框
//        options.setFreeStyleCropEnabled(true);
//
//        //设置最大缩放比例
//        options.setMaxScaleMultiplier(5);
//        //设置图片在切换比例时的动画
//        options.setImageToCropBoundsAnimDuration(666);
//
//        //设置是否展示矩形裁剪框
//        options.setShowCropFrame(false);
//        //设置裁剪框横竖线的宽度
//        options.setCropGridStrokeWidth(20);
//        //设置裁剪框横竖线的颜色
//        options.setCropGridColor(Color.GREEN);
//        //设置竖线的数量
//        options.setCropGridColumnCount(2);
//        //设置横线的数量
//        options.setCropGridRowCount(1);
        return options;
    }
}
