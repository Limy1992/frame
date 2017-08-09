package lmy.com.utilslib.base.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import io.reactivex.functions.Consumer;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.CommonManger;
import lmy.com.utilslib.utils.ToastUtils;
import lmy.com.utilslib.utils.Utils;

/**
 * 其他信息配置和界面跳转
 * Created by lmy on 2017/7/5
 */

public abstract class BaseTopActivity extends TopBarBaseActivity {
    @Override
    protected void setAdditionConfigure() {
        //配置其他操作
    }

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

    public void startAlbumAndCamera() {
        Matisse.from(this)
                .choose(MimeType.allOf())       //显示类型,
                .theme(R.style.Matisse_Dracula) //设置主题
                .countable(true)                //选择照片数字叠加
                .capture(true)                  //开启带有拍照
                 //将Uri的生成方式改为由FileProvider提供的临时授权路径 开启相册必须写此方法
                .captureStrategy(new CaptureStrategy(true, "com.lmy.audio.fileProvider"))
                .maxSelectable(9)               //照片可以选择多少个
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))        //显示网格照片,每个网络的大小dp单位
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())     //设置图片加载框架 Glide
                .forResult(CommonManger.REQUEST_CODE_CHOOSE);    // onActivityResult回调返回码
    }

    protected abstract void initData();
}
