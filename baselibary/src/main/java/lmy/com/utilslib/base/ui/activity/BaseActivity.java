package lmy.com.utilslib.base.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lmy.com.utilslib.R;

/**
 * tabBar事件
 * Created by lmy on 2017/7/25
 */

public abstract class BaseActivity extends BaseTopActivity {
    OnClickListener onClickListenerTopLeft;
    OnClickListener onClickListenerTopRight;

    int menuResId;
    String menuStr;
    private Unbinder bind;

    public interface OnClickListener {
        void onClick();
    }

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
        setTopLeftButton();
        init(savedInstanceState);
        initData();

    }

    protected abstract int getContentView();

    protected abstract String setTextTitle();

    protected abstract void init(Bundle savedInstanceState);

    //设置中间title
    protected void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    //子类重写此方法 更换左上角默认显示图片
    public void setReplaceTopLeftButton(int iconResId) {
        setTopLeftButton(iconResId, null);
    }

    //实现其他点击事件方法
    public void setReplaceTopLeftButton(int iconResId, OnClickListener onClickListener) {
        setTopLeftButton(iconResId, onClickListener);
    }

    private void setTopLeftButton() {
        setTopLeftButton(R.drawable.ic_return_white_24dp, null);
    }

    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener) {
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }

    //title右边内容
    protected void setTopRightButton(String menuStr, OnClickListener onClickListener) {
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    //title右边图片设置
    protected void setTopRightButton(String menuStr, int menuResId, OnClickListener onClickListener) {
        this.menuResId = menuResId;
        this.menuStr = menuStr;
        this.onClickListenerTopRight = onClickListener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)) {
            getMenuInflater().inflate(R.menu.menu_activity_base_top_bar, menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuResId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuResId);
        }
        if (!TextUtils.isEmpty(menuStr)) {
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (onClickListenerTopLeft != null) {
                onClickListenerTopLeft.onClick();
            } else {
                finish();       //不设置onClickListenerTopLeft监听，默认直接finish();
            }
        } else if (item.getItemId() == R.id.menu_1) {
            onClickListenerTopRight.onClick();
        }

        return true; // true 告诉系统我们自己处理了点击事件
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
