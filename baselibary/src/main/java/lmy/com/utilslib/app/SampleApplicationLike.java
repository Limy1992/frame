package lmy.com.utilslib.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.support.multidex.MultiDex;

import com.tencent.bugly.beta.Beta;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.Utils;

/**
 * Created by on 2018/1/22.
 *
 * @author lmy
 */

public class SampleApplicationLike extends DefaultApplicationLike {
     SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = Utils.getProcessName(getApplication(), Process.myPid());
        if (processName != null) {
            if (!processName.equals(getApplication().getPackageName())) {
                return;
            }
        }
        initApplication();
        //配置其他的初始化操作
        configureInitialization();
        QbSdk.initX5Environment(getApplication().getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
                LogUtils.d("X5内核="+b);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
//        Bugly.init(getApplication(), "858cc242dc", false);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

    protected void initApplication() {

    }

    /**
     * 配置其他初始化
     */
    protected void configureInitialization() {
    }
}

