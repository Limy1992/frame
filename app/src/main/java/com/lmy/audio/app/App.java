package com.lmy.audio.app;

import android.app.Application;
import android.content.Intent;


import com.squareup.leakcanary.LeakCanary;

import lmy.com.utilslib.app.BaseApplication;

/**
 * Created by on 2018/8/3.
 *
 * @author lmy
 */
public class App extends BaseApplication {
    public App(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (APP_DEBUG) {
            LeakCanary.install(getApplication());
        }
    }
}
