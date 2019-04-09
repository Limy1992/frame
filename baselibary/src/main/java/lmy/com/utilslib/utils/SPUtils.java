package lmy.com.utilslib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Set;

/**
 * Created by LMY on 2016/12/17.
 */

public class SPUtils {
    private static final String FILE_NAME_CONFIG = "wealth";        //sp文件名


    //存入String类型
    public static void putString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();


        edit.putString(key, value);
        //必须提交，否则结果无效
        edit.apply();
    }



    public static Set<String> getStringSet(Context context, String key, Set<String> defValues) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getStringSet(key, defValues);
    }

    public static void putStringSet(Context context, String key, Set<String> values) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putStringSet(key, values);
        //必须提交，否则结果无效
        edit.apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        // (String name xml文件名, int mode 访问权限)
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME_CONFIG,
                Context.MODE_PRIVATE);
        return prefs;
    }

    //清空sp里面的内容
    public static void clearSPData(Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.clear();
        edit.apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getBoolean(key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(key, value);
        //必须提交，否则结果无效
        edit.apply();
    }


}
