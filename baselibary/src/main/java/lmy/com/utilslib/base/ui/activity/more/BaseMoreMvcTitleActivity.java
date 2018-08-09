package lmy.com.utilslib.base.ui.activity.more;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewStub;

import com.chad.library.adapter.base.BaseQuickAdapter;

import lmy.com.utilslib.R;
import lmy.com.utilslib.mvp.base.view.IBaseMvpView;
import lmy.com.utilslib.utils.StatusBarUtil;

/**
 * 1、加载更多和刷刷新， 带有title  mvc
 * Created by on 2018/8/8.
 *
 * @author lmy
 */
public abstract class BaseMoreMvcTitleActivity extends SuperMoreActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener
        , IBaseMvpView {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }

    /**
     * 加载title控件
     */
    @Override
    protected void initViewStub() {
        try {
            ViewStub titleViewStub = findViewById(R.id.title_view_stub);
            titleViewStub.inflate();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initTitleBar() {
        //初始化设置 Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        //设置title
        setTitleText(setTextTitle());
        //默认每个页面都显示左上角关闭页面图片
        setReplaceTopLeftButton(R.drawable.my_back_b, null);
    }

    @Override
    protected void initOther() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.v3_app_new));
    }

    /**
     * 获取标题的高度
     */
    public int getTitleHight() {
        int height = toolbar.getHeight();
        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return height + rectangle.top;

    }

}
