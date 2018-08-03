package lmy.com.utilslib.base.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;


import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.FileUtils;

import static lmy.com.utilslib.utils.CommonManger.REQUEST_CODE_CHOOSE;

/**
 * 公共方法界面跳转 基本操作初始化
 * Created by lmy on 2017/7/5
 *
 * @author lmy
 */

public abstract class BasePagerJumpActivity extends BaseOtherActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(getContentView(), viewContent);
        bind = ButterKnife.bind(this);

        //初始化titleBar
        initTitleBar();

        //初始化操作
        init(savedInstanceState);

        //mvp
        initMvpPresenter();

        //网络加载
        initData();

        //初始化其他操作
        initOther();
    }

    /**
     * 所有事件完毕，  初始化其他， 重写此方法
     */
    protected void initOther() {

    }

    /**
     * 创建P层对象
     */
    protected void initMvpPresenter() {
    }

    /**
     * 如果需要初始化titleBar重写
     */
    protected void initTitleBar() {

    }

    /**
     * 获取setContentView
     *
     * @return view
     */
    protected abstract int getContentView();

    /**
     * title
     *
     * @return title
     */
    protected String setTextTitle() {
        return "";
    }

//    protected String setTextTitle(){
//        return "";
//    }

//    protected int setTextTitle(){
//        return 0;
//    }

    /**
     * 初始化
     *
     * @param savedInstanceState 状态保存
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 跳转
     *
     * @param activity activity
     */
    public void startNextActivity(Class activity) {
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
    }

    /**
     * 跳转 传值
     *
     * @param bundle   Bundle
     * @param activity activity
     */
    public void startNextActivity(Bundle bundle, Class activity) {
        Intent intent = new Intent(mContext, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转返回 回调
     *
     * @param activity    activity
     * @param requestCode 请求码
     */
    public void startNextActivity(Class activity, int requestCode) {
        Intent intent = new Intent(mContext, activity);
        startActivityForResult(intent, requestCode);
    }

    public void startNextActivity(Bundle bundle, Class activity, int requestCode) {
        Intent intent = new Intent(mContext, activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public Intent startIntentService(Bundle bundle, Class service) {
        Intent intent = new Intent(mContext, service);
        intent.putExtras(bundle);
        startService(intent);
        return intent;
    }

    public void startNextActivity(String pager, Bundle bundle) {
        Intent intent = new Intent(pager);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 链接启动activity
     * @param uriPath 路径地址
     */
    public void startNextActivity(String uriPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriPath));
        startActivity(intent);
    }

    public String getIntents(String key) {
        return getIntent().getStringExtra(key);
    }

    /**
     * 网络错误页面加载
     */
    public void nextErrPager() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_next_err_pager, null);
        //事件操作
        againLoadData(view);
    }

    /**
     * 网络错误页面加载自定义布局
     *
     * @param layoutRes 布局
     */
    public void nextErrPager(@LayoutRes int layoutRes) {
        View view = LayoutInflater.from(this).inflate(layoutRes, null);
        againLoadData(view);
    }

    /**
     * 网络错误页面加载自定义布局
     *
     * @param view 布局
     */
    public void nextErrPager(View view) {
        againLoadData(view);
    }

    /**
     * 点击重新获取
     *
     * @param view 布局
     */
    private void againLoadData(final View view) {
        viewContent.addView(view);
        ImageView nextErrPager = view.findViewById(R.id.net_err);
        nextErrPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContent.removeViewInLayout(view);
                initData();
            }
        });
    }

    /**
     * 动态权限管理sd卡操作
     */
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
                .captureStrategy(new CaptureStrategy(true, "com.nbd_audio_tools.fileProvider"))
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
