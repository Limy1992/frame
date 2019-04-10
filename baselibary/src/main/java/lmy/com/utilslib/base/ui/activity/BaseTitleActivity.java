package lmy.com.utilslib.base.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.ViewStub;

import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.StatusBarUtil;

/**
 * 带有titleBar的activity
 *
 * @author lmy
 *         Created by 2017/12/7
 */

public abstract class BaseTitleActivity extends SuperInitActivity {
    @Override
    protected void initViewStub() {
        try {
            ViewStub titleViewStub = (ViewStub) findViewById(R.id.title_view_stub);
            titleViewStub.inflate();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initTitleBar() {
        //初始化设置 Toolbar
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        }
        //设置title
        setTitleText(setTextTitle());
        //默认每个页面都显示左上角关闭页面图片
        setReplaceTopLeftButton(R.drawable.my_back_b, null);
    }

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }

    @Override
    protected void initOther() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.v3_app_new));
    }

    public int getTitleHight() {
        int height = toolbar.getHeight();
        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return height + rectangle.top;

    }
}
