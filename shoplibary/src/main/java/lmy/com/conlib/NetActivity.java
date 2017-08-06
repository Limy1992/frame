package lmy.com.conlib;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.github.mzule.activityrouter.annotation.Router;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


import io.reactivex.Observable;
import lmy.com.conlib.bean.DaBean;
import lmy.com.conlib.bean.DaTwoBean;
import lmy.com.conlib.bean.PrententBean;
import lmy.com.utilslib.base.ui.activity.BaseActivity;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.bean.DataBean;
import lmy.com.utilslib.net.ActivityLifeCycleEvent;
import lmy.com.utilslib.net.Api;
import lmy.com.utilslib.net.HttpUtil;
import lmy.com.utilslib.net.ProgressSubscriber;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.NetworkAvailableUtils;


/**
 * Created by lmy on 2017/7/17
 */

@Router("net")
public class NetActivity extends BaseActivity {

    @BindView(R2.id.tv)
    public TextView tv;

    @Inject
    PrententBean bean;

    @Override
    protected int getContentView() {
        return R.layout.shop_net_activity;
    }

    @Override
    protected String setTextTitle() {
        return "Net";
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        boolean networkAvailable = NetworkAvailableUtils.isNetworkAvailable();
        LogUtils.e("net=" + networkAvailable);
        DaggerNetComponent.builder()
                .daBean(new DaBean("123456"))
                .daTwoBean(new DaTwoBean(13))
                .build()
                .inject(this);


        LogUtils.e("s=" + bean.getS());
        LogUtils.e("s=" + bean.getAge());


        String str = getIntents("str");
        if (str != null) {
            tv.setText(str);
        }
    }

    @Override
    protected void initData() {
        Observable<BaseHttpResult<List<DataBean.ListBean>>> serviceData = Api.getDefault().getServiceData("f348719c-80da-45d0-a7c6-fbd9e8dc28c0");
        HttpUtil.getInstance()
                .toSubscribe(serviceData, new ProgressSubscriber<List<DataBean.ListBean>>(this) {
                    @Override
                    protected void _onNext(List<DataBean.ListBean> o) {
                        tv.setText(o.toString());

                    }

                    @Override
                    protected void _onError(String message) {
                        tv.setText(message);
                        nextErrPager();
                    }

                }, "cacheKey", ActivityLifeCycleEvent.DESTROY, lifecycleSubject, false, true);
    }

    public void netClick(View view) {
        startNextActivity(ShopCarActivity.class);
    }

}
