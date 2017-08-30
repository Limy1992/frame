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


import butterknife.ButterKnife;
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
 * 公共方法界面跳转 基本操作初始化
 * Created by lmy on 2017/7/5
 */

public abstract class BasePagerJumpActivity extends BaseOtherActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(getContentView(), viewContent);
        bind = ButterKnife.bind(this);
        //初始化设置 Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        //设置title
        setTitleText(setTextTitle());
        //默认每个页面都显示左上角关闭页面图片
        setTopLeftButton(R.drawable.ic_return_white_24dp, null);
        //初始化操作
        init(savedInstanceState);
        //网络加载
        initData();
    }

    protected abstract int getContentView();

    protected abstract String setTextTitle();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void initData();

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

    // 开启知乎三方库相册选择器
    public void startAlbumAndCamera() {
        Matisse.from(this)
                .choose(MimeType.ofAll(), false)    //选着类型
                .theme(R.style.Matisse_Dracula)
                .countable(true)                    //数字叠加
                .capture(true)                      //开启相机
                .captureStrategy(new CaptureStrategy(true, "com.lmy.audio.fileProvider"))   //共享路径
                .maxSelectable(1)                   //最大9张
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size)) //网络大小
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine()) //Glide加载
                .forResult(REQUEST_CODE_CHOOSE);
    }

}
