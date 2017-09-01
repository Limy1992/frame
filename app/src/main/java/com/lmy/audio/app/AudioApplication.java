package com.lmy.audio.app;

import com.squareup.leakcanary.LeakCanary;

import lmy.com.utilslib.app.ConfigureApplication;

/**
 * 有些jar包添加到model组件里没有效果, 需要添加主工程包使用此application
 * Created by lmy on 2017/9/1
 */

public class AudioApplication extends ConfigureApplication {
    @Override
    protected void lackMemory() {
        LeakCanary.install(this);
    }
}
