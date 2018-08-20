package lmy.com.utilslib.base.ui.fragment;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.lang.reflect.Field;
import java.util.Set;

import io.reactivex.functions.Consumer;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.ToastUtils;
import lmy.com.utilslib.utils.Utils;

import static lmy.com.utilslib.utils.CommonManger.REQUEST_CODE_CHOOSE;

/**
 * 跳转
 * Created by on 2018/4/18.
 *
 * @author lmy
 */
public abstract class BaseCommonFragment extends BaseHomeFragment {

    public void startNextActivity(Class activity) {
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
    }

    public void startNextActivity(String activityUrl) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(activityUrl));
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }

    public void startNextActivity(Bundle bundle, Class activity) {
        Intent intent = new Intent(mContext, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void startNextActivity(Bundle bundle, String nextUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(nextUrl));
        intent.putExtras(bundle);
        startActivity(intent);
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
    public void startAlbum(final String fileType) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            ToastUtils.showShortToast("相册打开失败");
            return;
        }
        final RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            //允许
                            startAlbumAndCamera(fileType); //带有拍照
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //拒绝
                            ToastUtils.showShortToast("拒绝权限");
                        } else {
                            //不在提示
                            ToastUtils.showShortToast("不在提示权限");
                        }
                    }
                });
    }



    /**
     * 开启知乎三方库相册选择器
     * @param fileType 打开类型
     */
    public void startAlbumAndCamera(String fileType) {
        Set<MimeType> mimeTypes ;

        if (fileType.equals("VIDEO")) {
            mimeTypes = MimeType.ofVideo();
        }else {
            mimeTypes = MimeType.ofImage();
        }

        startPhoto(mimeTypes);
    }


    private void startPhoto(Set<MimeType> mimeTypes) {
        Matisse.from(this)
                //选着类型
                .choose(mimeTypes, false)
                .theme(R.style.Matisse_Dracula)
                //数字叠加
                .countable(true)
                //开启相机
                .capture(true)
                //共享路径
                .captureStrategy(new CaptureStrategy(true, "com.bluedancer.bluedancer.fileProvider"))
                //最大多少张
                .maxSelectable(1)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                //网络大小
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                //Glide加载
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }
}
