package lmy.com.conlib.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 翻转动画
 * Created by lmy on 2017/8/3
 */

public class ImageViewAnimator extends ValueAnimator {
    private float startA = 0;
    private float endA = 180;
    private OnAnimationListener mOnAnimationListener;

    public ImageViewAnimator(FrameLayout lv) {
        initAnimator(lv);
    }

    public void initAnimator(final View lv) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(lv, "rotationY", startA, endA);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(lv, "scaleX", 1.0f, 0.5f, 1.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(lv, "scaleY", 1.0f, 0.5f, 1.0f);
        //存入属性动画集合
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        //同时执行多个动画
        animatorSet.playTogether(animator, animator2, animator3);
        //动画监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (Math.abs(animatedValue) > 90f && Math.abs(animatedValue) < 100f) {
                    //控件显示隐藏操作
                    mOnAnimationListener.onOneMiddle();
                }

                if (Math.abs(animatedValue) > 270f && Math.abs(animatedValue) < 280f) {
                    mOnAnimationListener.onTwoMiddle();
                }
            }

        });
        //监听器
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {  //开始
                lv.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {    //结束
                startA += endA;
                endA += endA;
                if (startA > 180) {
                    startA = 0;
                }
                if (endA > 360) {
                    endA = 180;
                }
                lv.setEnabled(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("tag", "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("tag", "onAnimationRepeat=");
            }
        });
        animatorSet.start();
    }


    public void setOnAnimationListener(OnAnimationListener onAnimationListener){
        this.mOnAnimationListener = onAnimationListener;
    }

    public interface OnAnimationListener {
        void onOneMiddle();     //第一次转的一半

        void onTwoMiddle();     //第二次转的一半
    }

}
