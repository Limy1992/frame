package com.lmy.audio.app;

import android.app.Application;
import android.content.Intent;

import com.squareup.leakcanary.LeakCanary;

import lmy.com.utilslib.app.InitApplication;
import lmy.com.utilslib.utils.LogUtils;

/**
 * Created by on 2018/8/3.
 *
 * @author lmy
 */
public class App extends InitApplication {
    public App(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("App");
    }
}
