package com.lmy.audio.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import java.util.List;

import lmy.com.utilslib.R;
import lmy.com.utilslib.adapter.HomeViewPagerAdapter;
import lmy.com.utilslib.base.ui.view.BottomTabView;
import lmy.com.utilslib.base.ui.view.HomeViewPager;

/**
 * home底部button
 * Created by lmy on 2017/7/4
 */

public abstract class MainCompatActivity extends AppCompatActivity {
    public HomeViewPager viewPager;
    public BottomTabView bottomTabView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_bottom_tab);

        permissionsPrompt();

        initView();

        initAdapter();

        initTab();
        bottomTabView.setUpWithViewPager(viewPager);
        initOr();
    }

    protected void permissionsPrompt() {
    }


    protected void initOr() {
    }


    private void initView() {
        viewPager = (HomeViewPager) findViewById(R.id.viewPager);
        bottomTabView = (BottomTabView) findViewById(R.id.bottomTabView);
        viewPager.setNoScroll(true);
    }


    private void initAdapter() {
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager.setAdapter(adapter);
        //缓存指定个数fragment界面，可根据需求而定
        viewPager.setOffscreenPageLimit(3);
    }

    private void initTab() {
        //设置tab
        if (getCenterView() == null) {
            bottomTabView.setTabItemViews(getTabViews());
        } else {
            bottomTabView.setTabItemViews(getTabViews(), getCenterView());
        }
    }

    //底部button
    protected abstract List<BottomTabView.TabItemView> getTabViews();

    //fragment页面
    protected abstract List<Fragment> getFragments();

    //底部按钮,中间button
    protected View getCenterView() {
        return null;
    }

}
