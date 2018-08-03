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
    public static final String ACCOUNT_ID = "accunt_id";                       //AccountId  在我的账号里面用到
    public static final String USER_ID = "user_id";                       //UserId  常用
    public static final String USER_NAME = "user_name";                       //name
    public static final String USER_ICON = "user_iocn";                       //头像
    public static final String IS_LOGIN = "isLogin";                //登录状态
    public static final String USER_PHONE = "user_phone";           //用户手机号
    public static final String NI_CHENG = "nicheng";           //昵称
    public static final String LOGINTOKEN = "logintoken";           //LOGINTOKEN
    public static final String PLAYER = "video";           //视频播放的URL
    public static final String IS_HOME_BOOLEAN = "is_home_boolean";           //如果是mian按钮点击设置true
    public static final String OPENID = "openId";           //第三方登录openId
    public static final String TOKEN = "token";           //第三方登录token
    public static final String APPLY_STATE = "authenticationFlag";           //用户是否申请认证通过
    public static final String APPLY_P_A = "authenticationType";           //申请的是个人还是机构
    public static final String APPLY_P_IS = "authenticationApplicationFlag";           //用户有没有认证

    public static final String LOGIN_STATUES = "login_statues"; //让用户登录改变状态

    /**
     * 融云token
     */
    public static final String RONG_TOKEN = "rong_token";


    public static String getLoginStatues(Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getString(LOGIN_STATUES, "");
    }

    //获取融云Token
    public static String getRongToken(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(RONG_TOKEN, "");
    }

    //AccountId
    public static String getAccountId(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return TextUtils.isEmpty(prefs.getString(ACCOUNT_ID, "")) ? "0" : prefs.getString(ACCOUNT_ID, "");
    }

    //用户是否申请认证通过
    public static String getAuthenticationFlag(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(APPLY_STATE, "");
    }

    //用户有没有认证
    public static String getAuthenticationApplicationFlag(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(APPLY_P_IS, "");
    }

    //申请的是个人还是机构
    public static String getAuthenticationType(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(APPLY_P_A, "");
    }

    //第三方登录token
    public static String getToken(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(TOKEN, "");
    }

    //userId
    public static String getUserId(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return TextUtils.isEmpty(prefs.getString(USER_ID, "")) ? "0" : prefs.getString(USER_ID, "");
    }

    //第三方登录openId
    public static String getOpenId(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(OPENID, "");
    }

    //name
    public static String getUserName(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(USER_NAME, "");
    }

    //头像
    public static String getUserIcon(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(USER_ICON, "");
    }

    //player
    public static String getVideoUrl(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(PLAYER, "");
    }


    //是否登录
    public static String getIsLogin(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(IS_LOGIN, "");
    }

    //获取用户手机号
    public static String getUserPhone(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(USER_PHONE, "");
    }

    //获取昵称
    public static String getUserNiCheng(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(NI_CHENG, "");
    }

    //LoginToKen
    public static String getLoginToKen(Context context) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(LOGINTOKEN, "");
    }

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
