package lmy.com.utilslib.app;


import android.app.Application;
import android.content.Intent;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import lmy.com.utilslib.utils.LogUtils;

/**
 * @author lmy
 *         Created by 2017/11/29
 */

public class InitApplication extends BaseApplication {

    public InitApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    protected void initFrame() {
        LogUtils.d("APP启动");
        if (APP_DEBUG) {
            LeakCanary.install(getApplication());
            //调试
            Stetho.initializeWithDefaults(getApplication());
        }
    }
}
