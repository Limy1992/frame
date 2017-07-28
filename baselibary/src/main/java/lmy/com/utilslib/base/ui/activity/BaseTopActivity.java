package lmy.com.utilslib.base.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.Utils;

/**
 * 其他信息配置和界面跳转
 * Created by lmy on 2017/7/5
 */

public abstract class BaseTopActivity extends TopBarBaseActivity {
    @Override
    protected void setAdditionConfigure() {
        //配置其他操作
    }

    //跳转
    public void startNextActivity(Class activity) {
        Intent intent = new Intent(Utils.getContext(), activity);
        startActivity(intent);
    }

    //跳转 传值
    public void startNextActivity(Bundle bundle, Class activity) {
        Intent intent = new Intent(Utils.getContext(), activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //跳转返回 回调
    public void startNextActivity(Class activity, int requestCode) {
        Intent intent = new Intent(Utils.getContext(), activity);
        startActivityForResult(intent, requestCode);
    }

    public void startNextActivity(Bundle bundle, Class activity, int requestCode) {
        Intent intent = new Intent(Utils.getContext(), activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public Intent startIntentService(Bundle bundle, Class service) {
        Intent intent = new Intent(Utils.getContext(), service);
        intent.putExtras(bundle);
        startService(intent);
        return intent;
    }

    public String getIntents(String key){
        return getIntent().getStringExtra(key);
    }

    //网络错误页面加载
    public void nextErrPager() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_next_err_pager, null);
        //事件操作
        againLoadData(view);

    }

    //网络错误页面加载自定义布局
    public void nextErrPager(@LayoutRes int layoutRes) {
        View view = LayoutInflater.from(this).inflate(layoutRes, null);
        againLoadData(view);
    }

    //点击重新获取
    private void againLoadData(final View view) {
        viewContent.addView(view);
        TextView next_err_pager = (TextView) view.findViewById(R.id.next_err_pager);
        next_err_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContent.removeViewInLayout(view);
                initData();
            }
        });
    }

    protected abstract void initData();
}
