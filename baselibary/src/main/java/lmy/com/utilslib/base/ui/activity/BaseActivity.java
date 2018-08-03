package lmy.com.utilslib.base.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

import butterknife.ButterKnife;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.StatusBarUtil;

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
