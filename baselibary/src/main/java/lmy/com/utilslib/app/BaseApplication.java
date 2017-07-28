package lmy.com.utilslib.app;

import android.app.Application;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;

import lmy.com.utilslib.utils.Utils;

/**
 * Application 基类
 * Created by lmy on 2017/7/14
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        webView();

        //配置其他的初始化操作
        configureInitialization();

    }

    private void webView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }

    public void configureInitialization() {
    }
}
