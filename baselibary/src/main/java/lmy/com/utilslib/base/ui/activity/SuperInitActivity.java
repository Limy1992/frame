package lmy.com.utilslib.base.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import lmy.com.utilslib.R;

/**
 * 公共方法界面跳转 基本操作初始化
 * Created by lmy on 2017/7/5
 *
 * @author lmy
 */

public abstract class SuperInitActivity extends SuperOtherActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        //将继承  的布局解析到 FrameLayout 里面
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
}
