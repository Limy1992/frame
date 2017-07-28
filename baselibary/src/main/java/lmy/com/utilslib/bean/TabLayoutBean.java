package lmy.com.utilslib.bean;


import android.support.v4.app.Fragment;

/**
 * TabLayout+viewPager  实体类
 * Created by lmy on 2017/7/12
 */

public class TabLayoutBean {
    public String tabLayoutTitle;
    public Fragment tabLayoutFragment;


    public TabLayoutBean(String tabLayoutTitle, Fragment tabLayoutFragment) {
        this.tabLayoutTitle = tabLayoutTitle;
        this.tabLayoutFragment = tabLayoutFragment;
    }
}
