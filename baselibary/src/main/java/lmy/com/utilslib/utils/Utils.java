package lmy.com.utilslib.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Utils初始化相关
 */
public class Utils {
    @SuppressLint("StaticFieldLeak")     //去除黄色警告
    private static Context context;     //注意使用,不会照成内存泄漏,

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    /**
     * 是否登录，如果没有登录跳转登录
     */
    public static boolean isLogin(){

        return false;
    }

    /**
     * @return 手机虚拟键的高度, 适配的时候需要
     */
    public static int getNavBarHeight() {
        return getTotalHeight() - getScreenHeight();
    }

    /**
     * @return 获取屏幕原始尺寸高度，包括虚拟功能键高度
     */
    private static int getTotalHeight() {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * @return 获取屏幕内容高度不包括虚拟按键
     */
    public static int getScreenNoHeight() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /*
     * 获取屏幕的宽*/
    public static int getScreenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 开启多进程，防止多次调用application
     *
     * @param pid 进程id
     * @return 进程名称
     */

    public static String getProcessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo prcInfo : runningApps) {
            if (prcInfo.pid == pid) {
                return prcInfo.processName;
            }
        }
        return null;
    }

    // 加载系统默认设置，字体不随用户设置变化
    public static void setToDefaults() {
        Resources res = context.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    /**
     * dp转换
     */
    public static int dip2px(float dpValue) {
        float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    /**
     * sp
     */
    public static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    public static String float2String(float value) {
        return String.valueOf(formatFloat(value));
    }

    private static float formatFloat(float value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getData(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTime(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * 获取系统手机版本号
     *
     * @return 版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static int getSystemSDK() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 根据视频路径获取视频图片
     *
     * @param videoPath 视频路径
     * @return 图片路径
     */
    public static String getVideosImagePath(String videoPath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(videoPath);
        // 取得视频的长度(单位为毫秒)
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        Bitmap bitmap = retriever.getFrameAtTime(TimeUnit.MILLISECONDS.toMicros(1));
        String pathUrl = Environment.getExternalStorageDirectory() + File.separator + 1 + ".jpg";
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pathUrl);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pathUrl;
    }

    /**
     * 获取版本名称
     */
    public static String getVersionName() {
        try {
            //获取包管理器
            PackageManager pm = context.getPackageManager();
            //获取包信息
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static String getPackgeName(){
        try {
            //获取包管理器
            PackageManager pm = context.getPackageManager();
            //获取包信息
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 转换货币金额格式
     *
     * @param string 需要转换的数据
     */
    public static String getMoneyType(String string) {
        // 把string类型的货币转换为double类型。
        Double numDouble = Double.parseDouble(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
        return format.format(numDouble);
    }

    /**
     * 转换货币金额格式
     *
     * @param numDouble 需要转换的数据
     */
    public static String getMoneyType(double numDouble) {
        // 把string类型的货币转换为double类型。
//        Double numDouble = Double.parseDouble(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
        return format.format(numDouble);
    }

    /**
     * 金额后2位
     */
    public static String numTextShow(String money) {
        BigDecimal b = new BigDecimal(money);
        double format = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(format);
    }


    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 判断service是否在运行
     *
     * @param className 包名+类名
     */
    public static boolean isWorked(String className) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 图片压缩
     *
     * @param path   路径
     * @param bitmap 图片
     */
    public static void fileString(String path, Bitmap bitmap) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 软键盘是否弹出
     * @param v view
     */
    public static boolean isSHowKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return false;
        }
        if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
            imm.showSoftInput(v, 0);
            return true;
            //软键盘已弹出
        } else {
            return false;
            //软键盘未弹出
        }
    }

}
