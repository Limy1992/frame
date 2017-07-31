package lmy.com.conlib;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import android.widget.TextView;

import com.github.mzule.activityrouter.annotation.Router;


import butterknife.BindView;
import lmy.com.utilslib.base.ui.activity.BaseActivity;
import lmy.com.utilslib.utils.LogUtils;

import lmy.com.utilslib.web.TenetWebView;
import lmy.com.utilslib.web.WebViewFile;


/**
 * Created by lmy on 2017/7/17
 */
@Router("shop")
public class ShopCarActivity extends BaseActivity {
    @BindView(R2.id.shop_tv)
    TextView shopTv;
    @BindView(R2.id.fr)
    FrameLayout lv;
    @BindView(R2.id.ll)
    ButtonView ll;
    private int rawY;

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
        LogUtils.e("name="+name);
        LogUtils.e("age="+age);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAni();
            }
        });
    }

    public void click(View view) {
        startNextActivity(TenetWebView.class);
//        startNextActivity(NetActivity.class);
    }



    public void startAni(){

        ObjectAnimator animator = ObjectAnimator.ofFloat(lv, "rotationY", 0f, 180f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(lv, "scaleX", 1.0f, 0.3f, 0.3f, 1.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(lv, "scaleY", 1.0f, 0.3f, 0.3f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
//        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(animator, animator2, animator3);
        animatorSet.start();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("dispatchTouchEvent_DOWN_activity");
                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtils.e("dispatchTouchEvent_ACTION_DOWN");
//                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("dispatchTouchEvent_UP_activity");
                break;
        }
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("onTouchEvent_DOWN_activity");
                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtils.e("dispatchTouchEvent_ACTION_DOWN");
//                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("onTouchEvent_UP_activity");
                break;
        }
        return super.onTouchEvent(event);
    }
}
