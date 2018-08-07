package lmy.com.utilslib.base.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


import java.util.List;

import butterknife.BindView;
import lmy.com.utilslib.R;
import lmy.com.utilslib.R2;
import lmy.com.utilslib.adapter.TabLayoutAdapter;

import lmy.com.utilslib.base.ui.activity.BaseTitleActivity;
import lmy.com.utilslib.bean.TabLayoutBean;

/**
 * TabLayout+viewPager 基类
 * Created by lmy on 2017/7/12
 */

public abstract class BaseTabLayoutActivity extends BaseTitleActivity {

    @BindView(R2.id.base_tab_layout)
    TabLayout baseTabLayout;
    @BindView(R2.id.base_view_pager)
    ViewPager baseViewPager;

    @Override
    protected int getContentView() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected String setTextTitle() {
        return getTextTitle();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TabLayoutAdapter layoutAdapter = new TabLayoutAdapter(getSupportFragmentManager());
        for (TabLayoutBean tabLayoutBean : addFragmentAndTitle()) {
            layoutAdapter.addFragment(tabLayoutBean.tabLayoutTitle, tabLayoutBean.tabLayoutFragment);
        }
        baseViewPager.setAdapter(layoutAdapter);
        baseTabLayout.setupWithViewPager(baseViewPager);
    }

//    public void setBarColor(){
//        StatusBarColorUtil.setActivityTranslucent(this);
//        toolbar.setVisibility(View.GONE);
//    }
    private List<TabLayoutBean> addFragmentAndTitle() {
        return getTabLayouts();
    }


    @Override
    protected void initData() {
    }

    //设置tabBar  title
    protected abstract String getTextTitle();

    //设置tabLayout title 和 viewpager里面的fragment
    protected abstract List<TabLayoutBean> getTabLayouts();
}
