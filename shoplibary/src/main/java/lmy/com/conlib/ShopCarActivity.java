package lmy.com.conlib;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import android.widget.ImageView;
import android.widget.TextView;

import com.github.mzule.activityrouter.annotation.Router;


import butterknife.BindView;
import lmy.com.conlib.view.ImageViewAnimator;
import lmy.com.utilslib.base.ui.activity.BaseActivity;
import lmy.com.utilslib.utils.LogUtils;

import lmy.com.utilslib.web.TenetWebView;


/**
 * Created by lmy on 2017/7/17
 */
@Router("shop")
public class ShopCarActivity extends BaseActivity {
    @BindView(R2.id.shop_tv)
    TextView shopTv;
    @BindView(R2.id.image_lv)
    ImageView imageLv;
    @BindView(R2.id.image_lv2)
    ImageView image_lv2;
    @BindView(R2.id.fr)
    FrameLayout lv;
    @BindView(R2.id.ll)
    ButtonView ll;

    private ImageViewAnimator animator;

    @Override
    protected int getContentView() {
        return R.layout.shop_car_activity;
    }

    @Override
    protected String setTextTitle() {
        return "ShopCarActivity";
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        shopTv.setText("shop组件");

    }

    @Override
    protected void initData() {
        String name = getIntents("name");
        String age = getIntents("age");
        LogUtils.e("name=" + name);
        LogUtils.e("age=" + age);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAni();
            }
        });

        //首次进来,先把盖在下面的图片旋转一下
        ObjectAnimator animator = ObjectAnimator.ofFloat(image_lv2, "rotationY", 0f, 180f);
        animator.start();
    }

    public void click(View view) {
        startNextActivity(TenetWebView.class);
    }


    public void startAni() {
        if (animator != null) {
            animator.initAnimator(lv);
            return;
        }

        animator = new ImageViewAnimator(lv);
        animator.setOnAnimationListener(new ImageViewAnimator.OnAnimationListener() {
            @Override
            public void onOneMiddle() {
                //控件显示隐藏操作
                imageLv.setVisibility(View.INVISIBLE);
                image_lv2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTwoMiddle() {
                imageLv.setVisibility(View.VISIBLE);
                image_lv2.setVisibility(View.INVISIBLE);
            }
        });
    }
}
