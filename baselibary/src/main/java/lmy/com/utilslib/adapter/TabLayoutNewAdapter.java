package lmy.com.utilslib.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * tabLayout+viewPager 不需要删除其他fragment页面的
 * Created by lmy on 2017/7/12
 */

public class TabLayoutNewAdapter extends FragmentStatePagerAdapter {
    private List<String> listTitle = new LinkedList<>();
    private List<Fragment> listFragment = new LinkedList<>();

    public TabLayoutNewAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(String title, Fragment fragment) {
        listTitle.add(title);
        listFragment.add(fragment);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
