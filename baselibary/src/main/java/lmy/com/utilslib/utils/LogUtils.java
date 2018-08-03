package lmy.com.utilslib.utils;

import android.util.Log;

import lmy.com.utilslib.app.BaseApplication;


/**
 * Log false打印
 * Created by lmy on 2017/7/12
 */

public class LogUtils {

    public static void e(String log){
        if (BaseApplication.APP_DEBUG) {
            Log.e("LoggingInterceptor", log);
        }
    }

    public static void d(String log){
        if (BaseApplication.APP_DEBUG) {
            Log.d("LoggingInterceptor", log);
        }
    }

    public static void el(String log){
        if (BaseApplication.APP_DEBUG) {
            Log.e("LoggingInterceptor", log);
        }
    }
}
