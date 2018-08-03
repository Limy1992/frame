package lmy.com.utilslib.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.LinkedList;
import java.util.List;

/**
 * tabLayout+viewPager
 * Created by lmy on 2017/7/12
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private List<String> listTitle = new LinkedList<>();
    private List<Fragment> listFragment = new LinkedList<>();

    public TabLayoutAdapter(FragmentManager fm) {
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
}
