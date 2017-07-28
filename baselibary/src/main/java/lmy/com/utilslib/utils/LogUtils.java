package lmy.com.utilslib.utils;

import android.util.Log;

/**
 * Log false打印
 * Created by lmy on 2017/7/12
 */

public class LogUtils {
    private static boolean isFlag;

    public static void e(String log){
        if (!isFlag) {
            Log.e("LogUtils", log);
        }
    }
}
