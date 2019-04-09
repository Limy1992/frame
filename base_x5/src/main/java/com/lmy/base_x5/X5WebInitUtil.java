package com.lmy.base_x5;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;


/**
 * X5初始化
 * CreateDate:2019/4/9
 * Author:lmy
 */
public class X5WebInitUtil {
    public static void getInstance(Context context){
        QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
                if (BuildConfig.DEBUG) {
                    Log.d("tag", "onViewInitFinished: x5初始化="+b);
                }
            }
        });
    }
}
