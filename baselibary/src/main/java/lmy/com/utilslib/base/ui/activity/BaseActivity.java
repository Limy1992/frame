package lmy.com.utilslib.base.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import butterknife.ButterKnife;
import lmy.com.utilslib.R;

/**
 * 其他子类activity继承BaseActivity
 * Created by lmy on 2017/7/25
 */

public abstract class BaseActivity extends BasePagerJumpActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }
}
