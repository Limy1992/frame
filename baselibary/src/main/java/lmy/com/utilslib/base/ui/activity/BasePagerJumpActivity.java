package lmy.com.utilslib.base.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;


import io.reactivex.functions.Consumer;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.ToastUtils;
import lmy.com.utilslib.utils.Utils;
import lmy.com.utilslib.zhihu.Matisse;
import lmy.com.utilslib.zhihu.MimeType;
import lmy.com.utilslib.zhihu.engine.impl.GlideEngine;
import lmy.com.utilslib.zhihu.internal.entity.CaptureStrategy;

import static lmy.com.utilslib.utils.CommonManger.REQUEST_CODE_CHOOSE;

/**
 * 一些公共方法和界面跳转
 * Created by lmy on 2017/7/5
 */

public abstract class BasePagerJumpActivity extends BaseOtherActivity {

    //跳转
    public void startNextActivity(Class activity) {
        Intent intent = new Intent(Utils.getContext(), activity);
        startActivity(intent);
    }

    //跳转 传值
    public void startNextActivity(Bundle bundle, Class activity) {
        Intent intent = new Intent(Utils.getContext(), activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //跳转返回 回调
    public void startNextActivity(Class activity, int requestCode) {
        Intent intent = new Intent(Utils.getContext(), activity);
        startActivityForResult(intent, requestCode);
    }

    public void startNextActivity(Bundle bundle, Class activity, int requestCode) {
        Intent intent = new Intent(Utils.getContext(), activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public Intent startIntentService(Bundle bundle, Class service) {
        Intent intent = new Intent(Utils.getContext(), service);
        intent.putExtras(bundle);
        startService(intent);
        return intent;
    }

    public void startNextActivity(String pager, Bundle bundle) {
        Intent intent = new Intent(pager);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public String getIntents(String key) {
        return getIntent().getStringExtra(key);
    }

    //网络错误页面加载
    public void nextErrPager() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_next_err_pager, null);
        //事件操作
        againLoadData(view);
    }

    //网络错误页面加载自定义布局
    public void nextErrPager(@LayoutRes int layoutRes) {
        View view = LayoutInflater.from(this).inflate(layoutRes, null);
        againLoadData(view);
    }

    //网络错误页面加载自定义布局
    public void nextErrPager(View view) {
        againLoadData(view);
    }

    //点击重新获取
    private void againLoadData(final View view) {
        viewContent.addView(view);
        TextView next_err_pager = (TextView) view.findViewById(R.id.next_err_pager);
        next_err_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContent.removeViewInLayout(view);
                initData();
            }
        });
    }

    protected abstract void initData();

    //动态权限管理sd卡操作
    public void startAlbum() {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            //允许
                            startAlbumAndCamera(); //带有拍照
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

    // 开启知乎三方库相册选择器
    public void startAlbumAndCamera() {
        Matisse.from(this)
                .choose(MimeType.ofAll(), false)    //选着类型
                .theme(R.style.Matisse_Dracula)
                .countable(true)                    //数字叠加
                .capture(true)                      //开启相机
                .captureStrategy(new CaptureStrategy(true, "com.lmy.audio.fileProvider"))   //共享路径
                .maxSelectable(9)                   //最大9张
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size)) //网络大小
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine()) //Glide加载
                .forResult(REQUEST_CODE_CHOOSE);
    }

    /**
     * 获取文件绝对路径
     *
     * @param url url
     * @return 返回文件本地真实路径
     */
    public String contentFile(Uri url) {
        String[] pro = {MediaStore.Images.Media.DATA};
        Cursor actualisation = managedQuery(url, pro, null, null, null);
        int actual_image_column_index = actualisation.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualisation.moveToFirst();
        return actualisation.getString(actual_image_column_index);
    }
}
