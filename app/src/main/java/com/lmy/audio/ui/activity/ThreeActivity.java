package com.lmy.audio.ui.activity;

import com.lmy.audio.ui.fragment.TabLayoutFragment1;
import com.lmy.audio.ui.fragment.TabLayoutFragment2;
import com.lmy.audio.ui.fragment.TabLayoutFragment3;

import java.util.ArrayList;
import java.util.List;

import lmy.com.utilslib.base.ui.fragment.BaseTabLayoutActivity;
import lmy.com.utilslib.bean.TabLayoutBean;


/**
 * Created by lmy on 2017/7/12
 */

public class ThreeActivity extends BaseTabLayoutActivity {

    @Override
    protected String getTextTitle() {
        return "ThreeActivity";
    }

    @Override
    protected List<TabLayoutBean> getTabLayouts() {
        List<TabLayoutBean> list = new ArrayList<>();
        list.add(new TabLayoutBean("标题一", new TabLayoutFragment1()));
        list.add(new TabLayoutBean("标题二", new TabLayoutFragment2()));
        list.add(new TabLayoutBean("标题三", new TabLayoutFragment3()));
        return list;
    }
}
