package lmy.com.utilslib.base.ui.activity;

import android.os.Bundle;

/**
 * 其他子类activity继承BaseActivity  不带有titleBar
 * Created by lmy on 2017/7/25
 *
 * @author lmy
 */

public abstract class BaseActivity extends BasePagerJumpActivity {
    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }
}
