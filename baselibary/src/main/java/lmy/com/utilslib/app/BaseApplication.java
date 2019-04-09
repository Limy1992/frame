package lmy.com.utilslib.app;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;

import com.base_dao.helper.DaoHelper;
import com.facebook.stetho.Stetho;
import com.lmy.base_x5.X5WebInitUtil;
import com.orhanobut.hawk.Hawk;

import lmy.com.utilslib.BuildConfig;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.Utils;

public class BaseApplication extends ConfigureApplication {

    //    private static DaoSession daoSession;
    public static final boolean APP_DEBUG = true;

    public BaseApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    protected void initApplication() {
        Utils.init(getApplication());
        Hawk.init(Utils.getContext()).build();
        //设置不随系统的设置字体改变
        Utils.setToDefaults();
        DaoHelper.getInstant(Utils.getContext());
        X5WebInitUtil.getInstance(Utils.getContext());
    }

    @Override
    public void configureInitialization() {
        if (APP_DEBUG) {
            //调试
            Stetho.initializeWithDefaults(getApplication());
        }
    }


    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        LogUtils.d("onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        LogUtils.d("onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        LogUtils.d("onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtils.d("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
