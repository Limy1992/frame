package com.lmy.audio.view;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.view.animation.DecelerateInterpolator;
import android.widget.ScrollView;

import lmy.com.utilslib.utils.LogUtils;


/**
 * 自定加载更和下拉刷新使用
 * Created by lmy on 2017/7/12
 */

public class CommonScrollView extends ScrollView {
    //防止多次调用
    private boolean isFlag;
    //提供接口方法
    private OnScrollChanged mOnScrollChanged;
    private int dowY;
    private int upFlo;


    public CommonScrollView(Context context) {
        this(context, null);
    }

    public CommonScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dowY = (int) ev.getY();
                LogUtils.e("按下Y值="+dowY);
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("移动Y值="+ev.getY());
                upFlo =(int) (ev.getY() - dowY);
                LogUtils.e(upFlo+"");
                if (upFlo > 0) {
                    setTranslationY(upFlo);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (upFlo > 0) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(this, "TranslationY", upFlo, 0f);
                    animator.setDuration(1000);
                    animator.setInterpolator(new DecelerateInterpolator());
                    animator.start();
                }
                break;
        }


        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = getChildAt(0);
        int b = view.getBottom();
        b -= (getHeight() + getScrollY());
        if (b == 0) {
            if (!isFlag) {
//                mOnScrollChanged.loadMore();
                isFlag = true;
                LogUtils.e("底部");
            }
        } else {
            isFlag = false;
//            LogUtils.e("非底部");
        }

        if (t == 0) {
//            mOnScrollChanged.loadRefresh();
            LogUtils.e("顶部");
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setOnScrollChanged(OnScrollChanged onScrollChanged) {
        this.mOnScrollChanged = onScrollChanged;
    }

    public interface OnScrollChanged {
        void loadMore();

        void loadRefresh();
    }
}
