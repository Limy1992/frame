package lmy.com.utilslib.base.ui.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.functions.Consumer;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.FileUtils;
import lmy.com.utilslib.utils.Utils;

import static lmy.com.utilslib.utils.CommonManger.REQUEST_CODE_CHOOSE;

/**
 * 配置其他三方初始化
 * Created by lmy on 2017/8/10
 * @author lmy
 */

public class SuperOtherActivity extends SuperToolbarActivity {
    @Override
    protected void setAdditionConfigure() {
        //配置其他操作
    }

    /***
     * 改变tabLayout的下划线宽度
     * @param tabLayout tablayout
     */
    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = Utils.dip2px(10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 是否滑动了底部
     *
     * @param recyclerView 列表控件
     * @return true
     */
    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    /**
     * 动态权限管理sd卡操作
     */
    @SuppressLint("CheckResult")
    public void startAlbum(final boolean isCapture, final int maxNum) {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(android.Manifest.permission.CAMERA
                ,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //允许
                            startAlbumAndCamera(isCapture, maxNum); //带有拍照
                        }
                    }
                });
    }

    /**
     * 开启知乎三方库相册选择器
     */
    public void startAlbumAndCamera(boolean isCapture, int maxNum) {
        Matisse.from(this)
                //选着类型
                .choose(MimeType.ofImage(), false)
                .theme(R.style.Matisse_Dracula)
                //数字叠加
                .countable(true)
                //开启相机
                .capture(isCapture)
                //共享路径
                .captureStrategy(new CaptureStrategy(true, "com.lmy.audio.fileProvider"))
                //最大多少张
                .maxSelectable(maxNum)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                //网络大小
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                //Glide加载
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    public void uarOptions(List<String> list, float floatY) {
        UCrop.of(Uri.fromFile(new File(list.get(0))), Uri.fromFile(new File(FileUtils.PHOTO+"/"+System.currentTimeMillis()+".jpg")))
                .withAspectRatio(16f, floatY)
                .withMaxResultSize(1080, 1920 / 2)
                .withOptions(options())
                .start(this);
    }

    private UCrop.Options options() {
        UCrop.Options options = new UCrop.Options();
        //设置titleBar颜色
        options.setToolbarColor(getResources().getColor(R.color.preview_bottom_toolbar_bg));
        //设置状态栏颜色
        options.setStatusBarColor(getResources().getColor(R.color.preview_bottom_toolbar_bg));
//        //设置裁剪图片可操作的手势
//        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
//        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
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
