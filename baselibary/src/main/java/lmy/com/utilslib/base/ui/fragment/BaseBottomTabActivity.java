package lmy.com.utilslib.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import java.util.List;

import lmy.com.utilslib.R;
import lmy.com.utilslib.adapter.HomeViewPagerAdapter;
import lmy.com.utilslib.view.BottomTabView;
import lmy.com.utilslib.view.HomeViewPager;

/**
 * home底部button
 * Created by lmy on 2017/7/4
 */

public abstract class BaseBottomTabActivity extends AppCompatActivity {
    HomeViewPager viewPager;
    BottomTabView bottomTabView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_bottom_tab);
        final TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        viewPager = (HomeViewPager) findViewById(R.id.viewPager);
        bottomTabView = (BottomTabView) findViewById(R.id.bottomTabView);
        viewPager.setNoScroll(true);

        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager.setAdapter(adapter);
        //缓存指定个数fragment界面，可根据需求而定
        viewPager.setOffscreenPageLimit(3);
        //设置tab
        if (getCenterView() == null){
            bottomTabView.setTabItemViews(getTabViews());
        }else {
            bottomTabView.setTabItemViews(getTabViews(), getCenterView());
        }
        tvTitle.setText(getTitleLists().get(0));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(getTitleLists().get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        bottomTabView.setUpWithViewPager(viewPager);

    }

    protected abstract List<BottomTabView.TabItemView> getTabViews();
    protected abstract List<Fragment> getFragments();
    protected abstract List<String> getTitleLists();

    protected View getCenterView(){
        return null;
    }

}
